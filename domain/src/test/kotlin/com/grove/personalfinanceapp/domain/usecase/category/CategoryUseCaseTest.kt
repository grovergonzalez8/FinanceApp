package com.grove.personalfinanceapp.domain.usecase.category

import com.grove.personalfinanceapp.domain.common.DomainError
import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.TransactionType
import com.grove.personalfinanceapp.domain.usecase.common.IdGenerator
import com.grove.personalfinanceapp.domain.usecase.support.FakeBudgetRepository
import com.grove.personalfinanceapp.domain.usecase.support.FakeCategoryRepository
import com.grove.personalfinanceapp.domain.usecase.support.FakeTransactionRepository
import com.grove.personalfinanceapp.domain.usecase.support.budget
import com.grove.personalfinanceapp.domain.usecase.support.category
import com.grove.personalfinanceapp.domain.usecase.support.transaction
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

class CategoryUseCaseTest {

    private val clock = Clock.fixed(
        Instant.parse("2026-04-14T12:00:00Z"),
        ZoneOffset.UTC,
    )

    @Test
    fun `create category always creates a non default category`() = runBlocking {
        val repository = FakeCategoryRepository()
        val useCase = CreateCategory(
            categoryRepository = repository,
            clock = clock,
            idGenerator = IdGenerator { "category-9" },
        )

        val result = useCase(
            CreateCategory.Input(
                userId = "user-1",
                name = " Travel ",
                type = TransactionType.EXPENSE,
            ),
        )

        assertTrue(result is DomainResult.Success)
        val createdCategory = (result as DomainResult.Success).value
        assertEquals("category-9", createdCategory.id)
        assertEquals("Travel", createdCategory.name)
        assertFalse(createdCategory.isDefault)
    }

    @Test
    fun `delete category fails when the category is linked to transactions`() = runBlocking {
        val categoryRepository = FakeCategoryRepository(
            initialCategories = listOf(category()),
        )
        val transactionRepository = FakeTransactionRepository(
            initialTransactions = listOf(transaction()),
        )
        val budgetRepository = FakeBudgetRepository()
        val useCase = DeleteCategory(
            categoryRepository = categoryRepository,
            transactionRepository = transactionRepository,
            budgetRepository = budgetRepository,
        )

        val result = useCase(
            DeleteCategory.Input(
                userId = "user-1",
                categoryId = "category-1",
            ),
        )

        assertTrue(result is DomainResult.Failure)
        assertTrue((result as DomainResult.Failure).error is DomainError.Conflict)
    }

    @Test
    fun `update category blocks type changes while the category is referenced`() = runBlocking {
        val categoryRepository = FakeCategoryRepository(
            initialCategories = listOf(category()),
        )
        val transactionRepository = FakeTransactionRepository(
            initialTransactions = listOf(transaction()),
        )
        val budgetRepository = FakeBudgetRepository(
            initialBudgets = listOf(budget()),
        )
        val useCase = UpdateCategory(
            categoryRepository = categoryRepository,
            transactionRepository = transactionRepository,
            budgetRepository = budgetRepository,
            clock = clock,
        )

        val result = useCase(
            UpdateCategory.Input(
                userId = "user-1",
                categoryId = "category-1",
                name = "Food",
                type = TransactionType.INCOME,
            ),
        )

        assertTrue(result is DomainResult.Failure)
        assertTrue((result as DomainResult.Failure).error is DomainError.Conflict)
    }
}
