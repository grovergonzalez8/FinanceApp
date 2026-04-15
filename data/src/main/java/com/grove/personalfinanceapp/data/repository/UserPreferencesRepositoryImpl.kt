package com.grove.personalfinanceapp.data.repository

import com.grove.personalfinanceapp.data.common.runDataOperation
import com.grove.personalfinanceapp.data.preferences.UserPreferencesDataStoreSource
import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.UserPreferences
import com.grove.personalfinanceapp.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepositoryImpl @Inject constructor(
    private val userPreferencesDataStoreSource: UserPreferencesDataStoreSource,
) : UserPreferencesRepository {

    override fun observeUserPreferences(userId: String): Flow<UserPreferences?> {
        return userPreferencesDataStoreSource.observeUserPreferences(userId)
    }

    override suspend fun saveUserPreferences(
        preferences: UserPreferences,
    ): DomainResult<UserPreferences> {
        return runDataOperation(defaultMessage = "Unable to save user preferences.") {
            userPreferencesDataStoreSource.saveUserPreferences(preferences)
            DomainResult.Success(preferences)
        }
    }
}
