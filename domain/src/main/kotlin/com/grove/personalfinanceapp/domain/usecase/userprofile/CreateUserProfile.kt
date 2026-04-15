package com.grove.personalfinanceapp.domain.usecase.userprofile

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.model.UserProfile
import com.grove.personalfinanceapp.domain.repository.UserProfileRepository
import com.grove.personalfinanceapp.domain.usecase.common.conflictFailure
import com.grove.personalfinanceapp.domain.usecase.common.normalizedOptionalCurrencyCode
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import com.grove.personalfinanceapp.domain.usecase.common.runUseCase
import kotlinx.coroutines.flow.first
import java.math.BigDecimal
import java.time.Clock

class CreateUserProfile(
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
            if (existingProfile != null) {
                return@runUseCase conflictFailure("A user profile already exists for this user.")
            }

            val now = clock.instant()
            val profile = UserProfile(
                id = userId,
                displayName = displayName,
                monthlyIncome = input.monthlyIncome,
                currencyCode = currencyCode,
                createdAt = now,
                updatedAt = now,
            )

            userProfileRepository.createUserProfile(profile)
        }
    }

    data class Input(
        val userId: String,
        val displayName: String,
        val monthlyIncome: BigDecimal?,
        val currencyCode: String?,
    )
}
