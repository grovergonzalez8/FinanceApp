package com.grove.personalfinanceapp.domain.repository

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.DashboardPeriod
import com.grove.personalfinanceapp.domain.model.DashboardSummary
import com.grove.personalfinanceapp.domain.model.FinancialInsight
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {
    fun observeDashboardSummary(
        userId: String,
        period: DashboardPeriod,
    ): Flow<DashboardSummary>

    suspend fun generateFinancialInsights(
        userId: String,
        period: DashboardPeriod,
    ): DomainResult<List<FinancialInsight>>
}
