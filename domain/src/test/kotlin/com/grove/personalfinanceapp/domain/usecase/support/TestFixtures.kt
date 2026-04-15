package com.grove.personalfinanceapp.domain.usecase.support

import com.grove.personalfinanceapp.domain.model.Budget
import com.grove.personalfinanceapp.domain.model.BudgetPeriod
import com.grove.personalfinanceapp.domain.model.Category
import com.grove.personalfinanceapp.domain.model.DateRange
import com.grove.personalfinanceapp.domain.model.FinancialTransaction
import com.grove.personalfinanceapp.domain.model.SavingsGoal
import com.grove.personalfinanceapp.domain.model.SavingsGoalPriority
import com.grove.personalfinanceapp.domain.model.TransactionType
import com.grove.personalfinanceapp.domain.model.UserProfile
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate

internal fun userProfile(
    id: String = "user-1",
    displayName: String = "Alex",
    monthlyIncome: BigDecimal? = BigDecimal("3000.00"),
    currencyCode: String? = "USD",
    createdAt: Instant = Instant.parse("2026-04-01T10:00:00Z"),
    updatedAt: Instant = createdAt,
): UserProfile {
    return UserProfile(
        id = id,
        displayName = displayName,
        monthlyIncome = monthlyIncome,
        currencyCode = currencyCode,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}

internal fun category(
    id: String = "category-1",
    userId: String = "user-1",
    name: String = "Food",
    type: TransactionType = TransactionType.EXPENSE,
    isDefault: Boolean = false,
    createdAt: Instant = Instant.parse("2026-04-01T10:00:00Z"),
    updatedAt: Instant = createdAt,
): Category {
    return Category(
        id = id,
        userId = userId,
        name = name,
        type = type,
        isDefault = isDefault,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}

internal fun transaction(
    id: String = "transaction-1",
    userId: String = "user-1",
    title: String = "Groceries",
    amount: BigDecimal = BigDecimal("50.00"),
    currencyCode: String = "USD",
    type: TransactionType = TransactionType.EXPENSE,
    categoryId: String = "category-1",
    occurredAt: Instant = Instant.parse("2026-04-10T08:00:00Z"),
    createdAt: Instant = Instant.parse("2026-04-10T08:00:00Z"),
    updatedAt: Instant = createdAt,
    note: String? = null,
): FinancialTransaction {
    return FinancialTransaction(
        id = id,
        userId = userId,
        title = title,
        note = note,
        amount = amount,
        currencyCode = currencyCode,
        type = type,
        categoryId = categoryId,
        occurredAt = occurredAt,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}

internal fun budget(
    id: String = "budget-1",
    userId: String = "user-1",
    categoryId: String = "category-1",
    limitAmount: BigDecimal = BigDecimal("400.00"),
    currencyCode: String = "USD",
    period: BudgetPeriod = BudgetPeriod.MONTHLY,
    range: DateRange = DateRange(
        startDate = LocalDate.parse("2026-04-01"),
        endDate = LocalDate.parse("2026-04-30"),
    ),
    createdAt: Instant = Instant.parse("2026-04-01T10:00:00Z"),
    updatedAt: Instant = createdAt,
): Budget {
    return Budget(
        id = id,
        userId = userId,
        categoryId = categoryId,
        limitAmount = limitAmount,
        currencyCode = currencyCode,
        period = period,
        range = range,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}

internal fun savingsGoal(
    id: String = "goal-1",
    userId: String = "user-1",
    name: String = "Emergency Fund",
    description: String? = null,
    targetAmount: BigDecimal = BigDecimal("1000.00"),
    currentAmount: BigDecimal = BigDecimal("200.00"),
    currencyCode: String = "USD",
    targetDate: LocalDate? = LocalDate.parse("2026-12-31"),
    priority: SavingsGoalPriority = SavingsGoalPriority.HIGH,
    createdAt: Instant = Instant.parse("2026-04-01T10:00:00Z"),
    updatedAt: Instant = createdAt,
): SavingsGoal {
    return SavingsGoal(
        id = id,
        userId = userId,
        name = name,
        description = description,
        targetAmount = targetAmount,
        currentAmount = currentAmount,
        currencyCode = currencyCode,
        targetDate = targetDate,
        priority = priority,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}
