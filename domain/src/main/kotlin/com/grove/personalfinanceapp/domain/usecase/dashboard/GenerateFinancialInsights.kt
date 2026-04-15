package com.grove.personalfinanceapp.domain.usecase.dashboard

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.model.DashboardPeriod
import com.grove.personalfinanceapp.domain.model.FinancialInsight
import com.grove.personalfinanceapp.domain.repository.DashboardRepository
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import com.grove.personalfinanceapp.domain.usecase.common.runUseCase

class GenerateFinancialInsights(
    private val dashboardRepository: DashboardRepository,
) {

    suspend operator fun invoke(input: Input): DomainResult<List<FinancialInsight>> {
        return runUseCase {
            val userId = input.userId.normalizedText()
            requireNotBlank(userId, "userId")

            dashboardRepository.generateFinancialInsights(
                userId = userId,
                period = input.period,
            )
        }
    }

    data class Input(
        val userId: String,
        val period: DashboardPeriod,
    )
}
