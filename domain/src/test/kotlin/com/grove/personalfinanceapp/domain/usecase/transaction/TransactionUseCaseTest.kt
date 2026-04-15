package com.grove.personalfinanceapp.domain.usecase.transaction

import com.grove.personalfinanceapp.domain.common.DomainError
import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.TransactionType
import com.grove.personalfinanceapp.domain.usecase.common.IdGenerator
import com.grove.personalfinanceapp.domain.usecase.support.FakeCategoryRepository
import com.grove.personalfinanceapp.domain.usecase.support.FakeTransactionRepository
import com.grove.personalfinanceapp.domain.usecase.support.category
import com.grove.personalfinanceapp.domain.usecase.support.transaction
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

class TransactionUseCaseTest {

    private val clock = Clock.fixed(
        Instant.parse("2026-04-14T12:00:00Z"),
        ZoneOffset.UTC,
    )

    @Test
    fun `add transaction rejects categories whose type does not match`() = runBlocking {
        val transactionRepository = FakeTransactionRepository()
        val categoryRepository = FakeCategoryRepository(
            initialCategories = listOf(
                category(type = TransactionType.INCOME),
            ),
        )
        val useCase = AddTransaction(
            transactionRepository = transactionRepository,
            categoryRepository = categoryRepository,
            clock = clock,
            idGenerator = IdGenerator { "transaction-9" },
        )

        val result = useCase(
            AddTransaction.Input(
                userId = "user-1",
                title = "Rent",
                note = null,
                amount = BigDecimal("900.00"),
                currencyCode = "USD",
                type = TransactionType.EXPENSE,
                categoryId = "category-1",
                occurredAt = Instant.parse("2026-04-14T09:00:00Z"),
            ),
        )

        assertTrue(result is DomainResult.Failure)
        val error = (result as DomainResult.Failure).error as DomainError.Validation
        assertEquals("categoryId", error.field)
    }

    @Test
    fun `update transaction preserves created at and refreshes updated at`() = runBlocking {
        val existingTransaction = transaction(
            createdAt = Instant.parse("2026-04-01T10:00:00Z"),
            updatedAt = Instant.parse("2026-04-01T10:00:00Z"),
        )
        val transactionRepository = FakeTransactionRepository(
            initialTransactions = listOf(existingTransaction),
        )
        val categoryRepository = FakeCategoryRepository(
            initialCategories = listOf(
                category(),
            ),
        )
        val useCase = UpdateTransaction(
            transactionRepository = transactionRepository,
            categoryRepository = categoryRepository,
            clock = clock,
        )

        val result = useCase(
            UpdateTransaction.Input(
                userId = "user-1",
                transactionId = "transaction-1",
                title = " Weekly groceries ",
                note = " restock ",
                amount = BigDecimal("72.50"),
                currencyCode = " usd ",
                type = TransactionType.EXPENSE,
                categoryId = "category-1",
                occurredAt = Instant.parse("2026-04-12T08:30:00Z"),
            ),
        )

        assertTrue(result is DomainResult.Success)
        val updatedTransaction = (result as DomainResult.Success).value
        assertEquals(existingTransaction.createdAt, updatedTransaction.createdAt)
        assertEquals(clock.instant(), updatedTransaction.updatedAt)
        assertEquals("Weekly groceries", updatedTransaction.title)
        assertEquals("restock", updatedTransaction.note)
        assertEquals("USD", updatedTransaction.currencyCode)
    }

    @Test
    fun `delete transaction returns not found when the transaction is outside the user scope`() = runBlocking {
        val transactionRepository = FakeTransactionRepository(
            initialTransactions = listOf(
                transaction(userId = "user-2"),
            ),
        )
        val useCase = DeleteTransaction(transactionRepository)

        val result = useCase(
            DeleteTransaction.Input(
                userId = "user-1",
                transactionId = "transaction-1",
            ),
        )

        assertTrue(result is DomainResult.Failure)
        val error = (result as DomainResult.Failure).error as DomainError.NotFound
        assertEquals("FinancialTransaction", error.entity)
        assertEquals("transaction-1", error.identifier)
    }
}
