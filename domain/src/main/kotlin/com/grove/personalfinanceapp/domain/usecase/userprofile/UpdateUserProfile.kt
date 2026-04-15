package com.grove.personalfinanceapp.domain.usecase.userprofile

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.model.UserProfile
import com.grove.personalfinanceapp.domain.repository.UserProfileRepository
import com.grove.personalfinanceapp.domain.usecase.common.normalizedOptionalCurrencyCode
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import com.grove.personalfinanceapp.domain.usecase.common.notFoundFailure
import com.grove.personalfinanceapp.domain.usecase.common.runUseCase
import kotlinx.coroutines.flow.first
import java.math.BigDecimal
import java.time.Clock

class UpdateUserProfile(
    private val userProfileRepository: UserProfileRepository,
    private val clock: Clock,
) {

    suspend operator fun invoke(input: Input): DomainResult<UserProfile> {
        return runUseCase {
            val userId = input.userId.normalizedText()
            val displayName = input.displayName.normalizedText()
            val currencyCode = input.currencyCode.normalizedOptionalCurrencyCode()

            requireNotBlank(userId, "userId")
            requireNotBlank(displayName, "displayName")

            val existingProfile = userProfileRepository.observeUserProfile(userId).first()
                ?: return@runUseCase notFoundFailure(
                    entity = "UserProfile",
                    identifier = userId,
                )

            val updatedProfile = existingProfile.copy(
                displayName = displayName,
                monthlyIncome = input.monthlyIncome,
                currencyCode = currencyCode,
                updatedAt = clock.instant(),
            )

            userProfileRepository.updateUserProfile(updatedProfile)
        }
    }

    data class Input(
        val userId: String,
        val displayName: String,
        val monthlyIncome: BigDecimal?,
        val currencyCode: String?,
    )
}
