package com.grove.personalfinanceapp.data.mapper

import com.grove.personalfinanceapp.data.local.entity.UserProfileEntity
import com.grove.personalfinanceapp.domain.model.UserProfile

fun UserProfileEntity.toDomain(): UserProfile {
    return UserProfile(
        id = id,
        displayName = displayName,
        monthlyIncome = monthlyIncome?.toBigDecimal(),
        currencyCode = currencyCode,
        createdAt = createdAtEpochMillis.toInstantValue(),
        updatedAt = updatedAtEpochMillis.toInstantValue(),
    )
}

fun UserProfile.toEntity(): UserProfileEntity {
    return UserProfileEntity(
        id = id,
        displayName = displayName,
        monthlyIncome = monthlyIncome?.toStorageString(),
        currencyCode = currencyCode,
        createdAtEpochMillis = createdAt.toEpochMillisValue(),
        updatedAtEpochMillis = updatedAt.toEpochMillisValue(),
    )
}
