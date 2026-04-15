package com.grove.personalfinanceapp.domain.usecase.budget

import com.grove.personalfinanceapp.domain.common.DomainError
import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.BudgetPeriod
import com.grove.personalfinanceapp.domain.model.DateRange
import com.grove.personalfinanceapp.domain.model.TransactionType
import com.grove.personalfinanceapp.domain.usecase.common.IdGenerator
import com.grove.personalfinanceapp.domain.usecase.support.FakeBudgetRepository
import com.grove.personalfinanceapp.domain.usecase.support.FakeCategoryRepository
import com.grove.personalfinanceapp.domain.usecase.support.budget
import com.grove.personalfinanceapp.domain.usecase.support.category
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

class BudgetUseCaseTest {

    private val clock = Clock.fixed(
        Instant.parse("2026-04-14T12:00:00Z"),
        ZoneOffset.UTC,
    )

    @Test
    fun `create budget rejects categories that are not expenses`() = runBlocking {
        val budgetRepository = FakeBudgetRepository()
        val categoryRepository = FakeCategoryRepository(
            initialCategories = listOf(
                category(type = TransactionType.INCOME),
            ),
        )
        val useCase = CreateBudget(
            budgetRepository = budgetRepository,
            categoryRepository = categoryRepository,
            clock = clock,
            idGenerator = IdGenerator { "budget-9" },
        )

        val result = useCase(
            CreateBudget.Input(
                userId = "user-1",
                categoryId = "category-1",
                limitAmount = BigDecimal("500.00"),
                currencyCode = "USD",
                period = BudgetPeriod.MONTHLY,
                range = DateRange(
                    startDate = LocalDate.parse("2026-04-01"),
                    endDate = LocalDate.parse("2026-04-30"),
                ),
            ),
        )

        assertTrue(result is DomainResult.Failure)
        val error = (result as DomainResult.Failure).error as DomainError.Validation
        assertEquals("categoryId", error.field)
    }

    @Test
    fun `create budget rejects invalid monthly ranges`() = runBlocking {
        val budgetRepository = FakeBudgetRepository()
        val categoryRepository = FakeCategoryRepository(
            initialCategories = listOf(category()),
        )
        val useCase = CreateBudget(
            budgetRepository = budgetRepository,
            categoryRepository = categoryRepository,
            clock = clock,
            idGenerator = IdGenerator { "budget-9" },
        )

        val result = useCase(
            CreateBudget.Input(
                userId = "user-1",
                categoryId = "category-1",
                limitAmount = BigDecimal("500.00"),
                currencyCode = "USD",
                period = BudgetPeriod.MONTHLY,
                range = DateRange(
                    startDate = LocalDate.parse("2026-04-05"),
                    endDate = LocalDate.parse("2026-04-30"),
                ),
            ),
        )

        assertTrue(result is DomainResult.Failure)
        val error = (result as DomainResult.Failure).error as DomainError.Validation
        assertEquals("range", error.field)
    }

    @Test
    fun `update budget preserves created at and refreshes updated at`() = runBlocking {
        val existingBudget = budget(
            createdAt = Instant.parse("2026-04-01T10:00:00Z"),
            updatedAt = Instant.parse("2026-04-01T10:00:00Z"),
        )
        val budgetRepository = FakeBudgetRepository(
            initialBudgets = listOf(existingBudget),
        )
        val categoryRepository = FakeCategoryRepository(
            initialCategories = listOf(category()),
        )
        val useCase = UpdateBudget(
            budgetRepository = budgetRepository,
            categoryRepository = categoryRepository,
            clock = clock,
        )

        val result = useCase(
            UpdateBudget.Input(
                userId = "user-1",
                budgetId = "budget-1",
                categoryId = "category-1",
                limitAmount = BigDecimal("650.00"),
                currencyCode = " usd ",
                period = BudgetPeriod.MONTHLY,
                range = DateRange(
                    startDate = LocalDate.parse("2026-04-01"),
                    endDate = LocalDate.parse("2026-04-30"),
                ),
            ),
        )

        assertTrue(result is DomainResult.Success)
        val updatedBudget = (result as DomainResult.Success).value
        assertEquals(existingBudget.createdAt, updatedBudget.createdAt)
        assertEquals(clock.instant(), updatedBudget.updatedAt)
        assertEquals(BigDecimal("650.00"), updatedBudget.limitAmount)
        assertEquals("USD", updatedBudget.currencyCode)
    }
}
