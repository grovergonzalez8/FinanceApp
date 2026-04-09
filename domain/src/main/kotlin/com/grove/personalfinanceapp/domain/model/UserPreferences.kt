package com.grove.personalfinanceapp.domain.model

import com.grove.personalfinanceapp.domain.common.requireNotBlank
import java.time.DayOfWeek

data class UserPreferences(
    val userId: String,
    val currencyCode: String,
    val defaultDashboardPeriod: DashboardPeriod,
    val showDashboardBalances: Boolean,
    val prioritizeSavingsGoals: Boolean,
    val weekStartsOn: DayOfWeek,
) {
    init {
        requireNotBlank(userId, "userId")
        requireNotBlank(currencyCode, "currencyCode")
    }
}
