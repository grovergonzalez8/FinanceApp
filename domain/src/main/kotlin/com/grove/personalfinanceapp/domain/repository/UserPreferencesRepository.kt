package com.grove.personalfinanceapp.domain.repository

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    fun observeUserPreferences(userId: String): Flow<UserPreferences?>

    suspend fun saveUserPreferences(preferences: UserPreferences): DomainResult<UserPreferences>
}
