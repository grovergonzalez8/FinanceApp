package com.grove.personalfinanceapp.domain.model

import com.grove.personalfinanceapp.domain.common.requireNonNegative
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import java.math.BigDecimal
import java.time.Instant

data class UserProfile(
    val id: String,
    val displayName: String,
    val monthlyIncome: BigDecimal?,
    val currencyCode: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    init {
        requireNotBlank(id, "id")
        requireNotBlank(displayName, "displayName")
        if (monthlyIncome != null) {
            requireNonNegative(monthlyIncome, "monthlyIncome")
            require(!currencyCode.isNullOrBlank()) {
                "currencyCode is required when monthlyIncome is provided."
            }
        }
        if (monthlyIncome == null) {
            require(currencyCode == null) {
                "currencyCode requires a monthlyIncome value."
            }
        }
    }
}
