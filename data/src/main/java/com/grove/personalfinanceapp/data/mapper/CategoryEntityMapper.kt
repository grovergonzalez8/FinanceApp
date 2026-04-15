package com.grove.personalfinanceapp.data.mapper

import com.grove.personalfinanceapp.data.local.entity.CategoryEntity
import com.grove.personalfinanceapp.domain.model.Category
import com.grove.personalfinanceapp.domain.model.TransactionType

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = id,
        userId = userId,
        name = name,
        type = TransactionType.valueOf(type),
        isDefault = isDefault,
        createdAt = createdAtEpochMillis.toInstantValue(),
        updatedAt = updatedAtEpochMillis.toInstantValue(),
    )
}

fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        userId = userId,
        name = name,
        type = type.name,
        isDefault = isDefault,
        createdAtEpochMillis = createdAt.toEpochMillisValue(),
        updatedAtEpochMillis = updatedAt.toEpochMillisValue(),
    )
}
