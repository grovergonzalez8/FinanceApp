package com.grove.personalfinanceapp.domain.usecase.preferences

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.model.DashboardPeriod
import com.grove.personalfinanceapp.domain.model.UserPreferences
import com.grove.personalfinanceapp.domain.repository.UserPreferencesRepository
import com.grove.personalfinanceapp.domain.usecase.common.normalizedCurrencyCode
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import com.grove.personalfinanceapp.domain.usecase.common.runUseCase
import java.time.DayOfWeek

class SaveUserPreferences(
    private val userPreferencesRepository: UserPreferencesRepository,
) {

    suspend operator fun invoke(input: Input): DomainResult<UserPreferences> {
        return runUseCase {
            val userId = input.userId.normalizedText()
            val currencyCode = input.currencyCode.normalizedCurrencyCode()

            requireNotBlank(userId, "userId")

            val preferences = UserPreferences(
                userId = userId,
                currencyCode = currencyCode,
                defaultDashboardPeriod = input.defaultDashboardPeriod,
                showDashboardBalances = input.showDashboardBalances,
                prioritizeSavingsGoals = input.prioritizeSavingsGoals,
                weekStartsOn = input.weekStartsOn,
            )

            userPreferencesRepository.saveUserPreferences(preferences)
        }
    }

    data class Input(
        val userId: String,
        val currencyCode: String,
        val defaultDashboardPeriod: DashboardPeriod,
        val showDashboardBalances: Boolean,
        val prioritizeSavingsGoals: Boolean,
        val weekStartsOn: DayOfWeek,
    )
}
