package com.grove.personalfinanceapp.domain.model

import com.grove.personalfinanceapp.domain.common.requireNonNegative
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import java.math.BigDecimal

data class DashboardSummary(
    val userId: String,
    val period: DashboardPeriod,
    val range: DateRange,
    val currencyCode: String,
    val totalIncome: BigDecimal,
    val totalExpenses: BigDecimal,
    val transactionCount: Int,
    val budgetProgress: List<BudgetProgress>,
    val topSpendingCategories: List<CategorySpending>,
    val prioritySavingsGoals: List<SavingsGoal>,
) {
    init {
        requireNotBlank(userId, "userId")
        requireNotBlank(currencyCode, "currencyCode")
        require(transactionCount >= 0) { "transactionCount cannot be negative." }
        requireNonNegative(totalIncome, "totalIncome")
        requireNonNegative(totalExpenses, "totalExpenses")
    }

    val netBalance: BigDecimal
        get() = totalIncome.subtract(totalExpenses)
}
