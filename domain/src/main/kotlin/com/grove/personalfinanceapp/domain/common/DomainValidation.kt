package com.grove.personalfinanceapp.domain.common

import java.math.BigDecimal

internal fun requireNotBlank(value: String, fieldName: String) {
    require(value.isNotBlank()) { "$fieldName cannot be blank." }
}

internal fun requirePositive(value: BigDecimal, fieldName: String) {
    require(value > BigDecimal.ZERO) { "$fieldName must be greater than zero." }
}

internal fun requireNonNegative(value: BigDecimal, fieldName: String) {
    require(value >= BigDecimal.ZERO) { "$fieldName cannot be negative." }
}

internal fun BigDecimal.coerceAtLeastZero(): BigDecimal {
    return if (this < BigDecimal.ZERO) BigDecimal.ZERO else this
}
