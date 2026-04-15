package com.grove.personalfinanceapp.data.mapper

import com.grove.personalfinanceapp.data.local.entity.BudgetEntity
import com.grove.personalfinanceapp.domain.model.Budget
import com.grove.personalfinanceapp.domain.model.BudgetPeriod
import com.grove.personalfinanceapp.domain.model.DateRange

fun BudgetEntity.toDomain(): Budget {
    return Budget(
        id = id,
        userId = userId,
        categoryId = categoryId,
        limitAmount = limitAmount.toBigDecimal(),
        currencyCode = currencyCode,
        period = BudgetPeriod.valueOf(period),
        range = DateRange(
            startDate = startDateEpochDay.toLocalDateValue(),
            endDate = endDateEpochDay.toLocalDateValue(),
        ),
        createdAt = createdAtEpochMillis.toInstantValue(),
        updatedAt = updatedAtEpochMillis.toInstantValue(),
    )
}

fun Budget.toEntity(): BudgetEntity {
    return BudgetEntity(
        id = id,
        userId = userId,
        categoryId = categoryId,
        limitAmount = limitAmount.toStorageString(),
        currencyCode = currencyCode,
        period = period.name,
        startDateEpochDay = range.startDate.toEpochDayValue(),
        endDateEpochDay = range.endDate.toEpochDayValue(),
        createdAtEpochMillis = createdAt.toEpochMillisValue(),
        updatedAtEpochMillis = updatedAt.toEpochMillisValue(),
    )
}
