package com.grove.personalfinanceapp.domain.usecase.userprofile

import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.model.UserProfile
import com.grove.personalfinanceapp.domain.repository.UserProfileRepository
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import kotlinx.coroutines.flow.Flow

class GetUserProfile(
    private val userProfileRepository: UserProfileRepository,
) {

    operator fun invoke(userId: String): Flow<UserProfile?> {
        val normalizedUserId = userId.normalizedText()
        requireNotBlank(normalizedUserId, "userId")

        return userProfileRepository.observeUserProfile(normalizedUserId)
    }
}
