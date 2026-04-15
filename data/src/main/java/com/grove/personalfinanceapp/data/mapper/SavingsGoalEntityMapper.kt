package com.grove.personalfinanceapp.data.mapper

import com.grove.personalfinanceapp.data.local.entity.SavingsGoalEntity
import com.grove.personalfinanceapp.domain.model.SavingsGoal
import com.grove.personalfinanceapp.domain.model.SavingsGoalPriority

fun SavingsGoalEntity.toDomain(): SavingsGoal {
    return SavingsGoal(
        id = id,
        userId = userId,
        name = name,
        description = description,
        targetAmount = targetAmount.toBigDecimal(),
        currentAmount = currentAmount.toBigDecimal(),
        currencyCode = currencyCode,
        targetDate = targetDateEpochDay?.toLocalDateValue(),
        priority = SavingsGoalPriority.valueOf(priority),
        createdAt = createdAtEpochMillis.toInstantValue(),
        updatedAt = updatedAtEpochMillis.toInstantValue(),
    )
}

fun SavingsGoal.toEntity(): SavingsGoalEntity {
    return SavingsGoalEntity(
        id = id,
        userId = userId,
        name = name,
        description = description,
        targetAmount = targetAmount.toStorageString(),
        currentAmount = currentAmount.toStorageString(),
        currencyCode = currencyCode,
        targetDateEpochDay = targetDate?.toEpochDayValue(),
        priority = priority.name,
        createdAtEpochMillis = createdAt.toEpochMillisValue(),
        updatedAtEpochMillis = updatedAt.toEpochMillisValue(),
    )
}
