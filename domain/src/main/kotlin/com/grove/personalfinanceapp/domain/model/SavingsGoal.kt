package com.grove.personalfinanceapp.domain.model

import com.grove.personalfinanceapp.domain.common.coerceAtLeastZero
import com.grove.personalfinanceapp.domain.common.requireNonNegative
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.common.requirePositive
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate

data class SavingsGoal(
    val id: String,
    val userId: String,
    val name: String,
    val description: String?,
    val targetAmount: BigDecimal,
    val currentAmount: BigDecimal,
    val currencyCode: String,
    val targetDate: LocalDate?,
    val priority: SavingsGoalPriority,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    init {
        requireNotBlank(id, "id")
        requireNotBlank(userId, "userId")
        requireNotBlank(name, "name")
        requireNotBlank(currencyCode, "currencyCode")
        requirePositive(targetAmount, "targetAmount")
        requireNonNegative(currentAmount, "currentAmount")
    }

    val remainingAmount: BigDecimal
        get() = targetAmount.subtract(currentAmount).coerceAtLeastZero()

    val isCompleted: Boolean
        get() = currentAmount >= targetAmount
}
