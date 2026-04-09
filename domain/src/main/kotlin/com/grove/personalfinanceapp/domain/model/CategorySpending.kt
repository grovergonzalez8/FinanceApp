package com.grove.personalfinanceapp.domain.model

import com.grove.personalfinanceapp.domain.common.requireNonNegative
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import java.math.BigDecimal

data class CategorySpending(
    val categoryId: String,
    val categoryName: String,
    val totalAmount: BigDecimal,
    val currencyCode: String,
    val transactionCount: Int,
) {
    init {
        requireNotBlank(categoryId, "categoryId")
        requireNotBlank(categoryName, "categoryName")
        requireNotBlank(currencyCode, "currencyCode")
        require(transactionCount >= 0) { "transactionCount cannot be negative." }
        requireNonNegative(totalAmount, "totalAmount")
    }
}
