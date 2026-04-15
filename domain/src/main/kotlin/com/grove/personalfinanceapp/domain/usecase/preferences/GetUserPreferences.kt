package com.grove.personalfinanceapp.domain.usecase.preferences

import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.model.UserPreferences
import com.grove.personalfinanceapp.domain.repository.UserPreferencesRepository
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import kotlinx.coroutines.flow.Flow

class GetUserPreferences(
    private val userPreferencesRepository: UserPreferencesRepository,
) {

    operator fun invoke(userId: String): Flow<UserPreferences?> {
        val normalizedUserId = userId.normalizedText()
        requireNotBlank(normalizedUserId, "userId")

        return userPreferencesRepository.observeUserPreferences(normalizedUserId)
    }
}
