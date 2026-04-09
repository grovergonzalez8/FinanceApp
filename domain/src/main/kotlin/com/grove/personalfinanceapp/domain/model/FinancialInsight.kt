package com.grove.personalfinanceapp.domain.model

import com.grove.personalfinanceapp.domain.common.requireNonNegative
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import java.math.BigDecimal
import java.time.Instant

data class FinancialInsight(
    val id: String,
    val userId: String,
    val type: FinancialInsightType,
    val severity: InsightSeverity,
    val title: String,
    val message: String,
    val generatedAt: Instant,
    val relatedCategoryId: String? = null,
    val highlightAmount: BigDecimal? = null,
    val currencyCode: String? = null,
) {
    init {
        requireNotBlank(id, "id")
        requireNotBlank(userId, "userId")
        requireNotBlank(title, "title")
        requireNotBlank(message, "message")
        if (highlightAmount != null) {
            requireNonNegative(highlightAmount, "highlightAmount")
            require(!currencyCode.isNullOrBlank()) {
                "currencyCode is required when highlightAmount is provided."
            }
        }
        if (highlightAmount == null) {
            require(currencyCode == null) {
                "currencyCode requires a highlightAmount."
            }
        }
    }
}
