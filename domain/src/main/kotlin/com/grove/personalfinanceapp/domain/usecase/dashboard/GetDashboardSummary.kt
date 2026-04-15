package com.grove.personalfinanceapp.domain.usecase.dashboard

import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.model.DashboardPeriod
import com.grove.personalfinanceapp.domain.model.DashboardSummary
import com.grove.personalfinanceapp.domain.repository.DashboardRepository
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import kotlinx.coroutines.flow.Flow

class GetDashboardSummary(
    private val dashboardRepository: DashboardRepository,
) {

    operator fun invoke(input: Input): Flow<DashboardSummary> {
        val userId = input.userId.normalizedText()
        requireNotBlank(userId, "userId")

        return dashboardRepository.observeDashboardSummary(
            userId = userId,
            period = input.period,
        )
    }

    data class Input(
        val userId: String,
        val period: DashboardPeriod,
    )
}
