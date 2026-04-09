package com.grove.personalfinanceapp.domain.model

import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.common.requirePositive
import java.math.BigDecimal
import java.time.Instant

data class FinancialTransaction(
    val id: String,
    val userId: String,
    val title: String,
    val note: String?,
    val amount: BigDecimal,
    val currencyCode: String,
    val type: TransactionType,
    val categoryId: String,
    val occurredAt: Instant,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    init {
        requireNotBlank(id, "id")
        requireNotBlank(userId, "userId")
        requireNotBlank(title, "title")
        requireNotBlank(currencyCode, "currencyCode")
        requireNotBlank(categoryId, "categoryId")
        requirePositive(amount, "amount")
    }

    val signedAmount: BigDecimal
        get() = if (type == TransactionType.EXPENSE) amount.negate() else amount
}
