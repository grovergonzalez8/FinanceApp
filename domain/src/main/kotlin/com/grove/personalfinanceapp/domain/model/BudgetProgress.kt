package com.grove.personalfinanceapp.domain.model

import com.grove.personalfinanceapp.domain.common.requireNonNegative
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.common.requirePositive
import java.math.BigDecimal
import java.math.RoundingMode

data class BudgetProgress(
    val budgetId: String,
    val categoryId: String,
    val categoryName: String,
    val limitAmount: BigDecimal,
    val spentAmount: BigDecimal,
    val currencyCode: String,
) {
    init {
        requireNotBlank(budgetId, "budgetId")
        requireNotBlank(categoryId, "categoryId")
        requireNotBlank(categoryName, "categoryName")
        requireNotBlank(currencyCode, "currencyCode")
        requirePositive(limitAmount, "limitAmount")
        requireNonNegative(spentAmount, "spentAmount")
    }

    val remainingAmount: BigDecimal
        get() = limitAmount.subtract(spentAmount)

    val utilizationRatio: BigDecimal
        get() = spentAmount.divide(limitAmount, 4, RoundingMode.HALF_UP)

    val isExceeded: Boolean
        get() = spentAmount > limitAmount
}
