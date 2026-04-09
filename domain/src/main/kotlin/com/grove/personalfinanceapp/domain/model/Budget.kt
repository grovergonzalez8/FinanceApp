package com.grove.personalfinanceapp.domain.model

import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.common.requirePositive
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate

data class Budget(
    val id: String,
    val userId: String,
    val categoryId: String,
    val limitAmount: BigDecimal,
    val currencyCode: String,
    val period: BudgetPeriod,
    val range: DateRange,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    init {
        requireNotBlank(id, "id")
        requireNotBlank(userId, "userId")
        requireNotBlank(categoryId, "categoryId")
        requireNotBlank(currencyCode, "currencyCode")
        requirePositive(limitAmount, "limitAmount")
    }

    fun isActiveOn(date: LocalDate): Boolean = range.contains(date)
}
