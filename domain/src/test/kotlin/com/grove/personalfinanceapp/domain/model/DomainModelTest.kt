package com.grove.personalfinanceapp.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate

class DomainModelTest {

    @Test
    fun `expense transactions expose a negative signed amount`() {
        val transaction = FinancialTransaction(
            id = "transaction-1",
            userId = "user-1",
            title = "Groceries",
            note = null,
            amount = BigDecimal("150.00"),
            currencyCode = "USD",
            type = TransactionType.EXPENSE,
            categoryId = "category-1",
            occurredAt = Instant.parse("2026-04-09T10:15:30Z"),
            createdAt = Instant.parse("2026-04-09T10:15:30Z"),
            updatedAt = Instant.parse("2026-04-09T10:15:30Z"),
        )

        assertEquals(BigDecimal("-150.00"), transaction.signedAmount)
    }

    @Test
    fun `savings goals clamp remaining amount at zero when overfunded`() {
        val goal = SavingsGoal(
            id = "goal-1",
            userId = "user-1",
            name = "Emergency Fund",
            description = null,
            targetAmount = BigDecimal("1000.00"),
            currentAmount = BigDecimal("1200.00"),
            currencyCode = "USD",
            targetDate = LocalDate.parse("2026-12-31"),
            priority = SavingsGoalPriority.HIGH,
            createdAt = Instant.parse("2026-04-09T10:15:30Z"),
            updatedAt = Instant.parse("2026-04-09T10:15:30Z"),
        )

        assertEquals(BigDecimal.ZERO, goal.remainingAmount)
        assertTrue(goal.isCompleted)
    }

    @Test
    fun `dashboard summary derives net balance from income and expenses`() {
        val summary = DashboardSummary(
            userId = "user-1",
            period = DashboardPeriod.MONTH,
            range = DateRange(
                startDate = LocalDate.parse("2026-04-01"),
                endDate = LocalDate.parse("2026-04-30"),
            ),
            currencyCode = "USD",
            totalIncome = BigDecimal("3000.00"),
            totalExpenses = BigDecimal("1850.50"),
            transactionCount = 24,
            budgetProgress = emptyList(),
            topSpendingCategories = emptyList(),
            prioritySavingsGoals = emptyList(),
        )

        assertEquals(BigDecimal("1149.50"), summary.netBalance)
    }

    @Test
    fun `budget progress reports when spending exceeds the budget`() {
        val progress = BudgetProgress(
            budgetId = "budget-1",
            categoryId = "category-1",
            categoryName = "Food",
            limitAmount = BigDecimal("500.00"),
            spentAmount = BigDecimal("625.00"),
            currencyCode = "USD",
        )

        assertEquals(BigDecimal("-125.00"), progress.remainingAmount)
        assertTrue(progress.isExceeded)
        assertFalse(progress.utilizationRatio < BigDecimal.ONE)
    }
}
