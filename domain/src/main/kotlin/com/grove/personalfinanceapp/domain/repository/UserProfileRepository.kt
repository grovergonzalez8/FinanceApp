package com.grove.personalfinanceapp.domain.repository

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {
    fun observeUserProfile(userId: String): Flow<UserProfile?>

    suspend fun createUserProfile(profile: UserProfile): DomainResult<UserProfile>

    suspend fun updateUserProfile(profile: UserProfile): DomainResult<UserProfile>
}
