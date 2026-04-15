package com.grove.personalfinanceapp.data.repository

import com.grove.personalfinanceapp.data.common.runDataOperation
import com.grove.personalfinanceapp.data.local.dao.UserProfileDao
import com.grove.personalfinanceapp.data.mapper.toDomain
import com.grove.personalfinanceapp.data.mapper.toEntity
import com.grove.personalfinanceapp.domain.common.DomainError
import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.UserProfile
import com.grove.personalfinanceapp.domain.repository.UserProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProfileRepositoryImpl @Inject constructor(
    private val userProfileDao: UserProfileDao,
) : UserProfileRepository {

    override fun observeUserProfile(userId: String): Flow<UserProfile?> {
        return userProfileDao.observeUserProfile(userId).map { entity -> entity?.toDomain() }
    }

    override suspend fun createUserProfile(profile: UserProfile): DomainResult<UserProfile> {
        return runDataOperation(defaultMessage = "Unable to create user profile.") {
            userProfileDao.insertUserProfile(profile.toEntity())
            DomainResult.Success(profile)
        }
    }

    override suspend fun updateUserProfile(profile: UserProfile): DomainResult<UserProfile> {
        return runDataOperation(defaultMessage = "Unable to update user profile.") {
            val rowsUpdated = userProfileDao.updateUserProfile(profile.toEntity())
            if (rowsUpdated == 0) {
                DomainResult.Failure(
                    error = DomainError.NotFound(
                        entity = "UserProfile",
                        identifier = profile.id,
                    ),
                )
            } else {
                DomainResult.Success(profile)
            }
        }
    }
}
