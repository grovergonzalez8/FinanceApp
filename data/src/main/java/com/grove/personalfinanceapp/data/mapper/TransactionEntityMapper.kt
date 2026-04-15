package com.grove.personalfinanceapp.data.mapper

import com.grove.personalfinanceapp.data.local.entity.TransactionEntity
import com.grove.personalfinanceapp.domain.model.FinancialTransaction
import com.grove.personalfinanceapp.domain.model.TransactionType

fun TransactionEntity.toDomain(): FinancialTransaction {
    return FinancialTransaction(
        id = id,
        userId = userId,
        title = title,
        note = note,
        amount = amount.toBigDecimal(),
        currencyCode = currencyCode,
        type = TransactionType.valueOf(type),
        categoryId = categoryId,
        occurredAt = occurredAtEpochMillis.toInstantValue(),
        createdAt = createdAtEpochMillis.toInstantValue(),
        updatedAt = updatedAtEpochMillis.toInstantValue(),
    )
}

fun FinancialTransaction.toEntity(): TransactionEntity {
    return TransactionEntity(
        id = id,
        userId = userId,
        title = title,
        note = note,
        amount = amount.toStorageString(),
        currencyCode = currencyCode,
        type = type.name,
        categoryId = categoryId,
        occurredAtEpochMillis = occurredAt.toEpochMillisValue(),
        createdAtEpochMillis = createdAt.toEpochMillisValue(),
        updatedAtEpochMillis = updatedAt.toEpochMillisValue(),
    )
}
