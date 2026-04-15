package com.grove.personalfinanceapp.data.repository

import com.grove.personalfinanceapp.data.common.runDataOperation
import com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao
import com.grove.personalfinanceapp.data.mapper.toDomain
import com.grove.personalfinanceapp.data.mapper.toEntity
import com.grove.personalfinanceapp.domain.common.DomainError
import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.SavingsGoal
import com.grove.personalfinanceapp.domain.repository.SavingsGoalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal
import java.time.Clock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SavingsGoalRepositoryImpl @Inject constructor(
    private val savingsGoalDao: SavingsGoalDao,
    private val clock: Clock,
) : SavingsGoalRepository {

    override fun observeSavingsGoals(userId: String): Flow<List<SavingsGoal>> {
        return savingsGoalDao.observeSavingsGoals(userId).map { entities ->
            entities.map { entity -> entity.toDomain() }
        }
    }

    override suspend fun createSavingsGoal(goal: SavingsGoal): DomainResult<SavingsGoal> {
        return runDataOperation(defaultMessage = "Unable to create savings goal.") {
            savingsGoalDao.insertSavingsGoal(goal.toEntity())
            DomainResult.Success(goal)
        }
    }

    override suspend fun updateSavingsGoal(goal: SavingsGoal): DomainResult<SavingsGoal> {
        return runDataOperation(defaultMessage = "Unable to update savings goal.") {
            val rowsUpdated = savingsGoalDao.updateSavingsGoal(goal.toEntity())
            if (rowsUpdated == 0) {
                DomainResult.Failure(
                    error = DomainError.NotFound(
                        entity = "SavingsGoal",
                        identifier = goal.id,
                    ),
                )
            } else {
                DomainResult.Success(goal)
            }
        }
    }

    override suspend fun addContribution(
        goalId: String,
        amount: BigDecimal,
    ): DomainResult<SavingsGoal> {
        return runDataOperation(defaultMessage = "Unable to add goal contribution.") {
            if (amount <= BigDecimal.ZERO) {
                return@runDataOperation DomainResult.Failure(
                    error = DomainError.Validation(
                        message = "amount must be greater than zero.",
                        field = "amount",
                    ),
                )
            }

            val existingGoal = savingsGoalDao.getSavingsGoalById(goalId)?.toDomain()
                ?: return@runDataOperation DomainResult.Failure(
                    error = DomainError.NotFound(
                        entity = "SavingsGoal",
                        identifier = goalId,
                    ),
                )

            val updatedGoal = existingGoal.copy(
                currentAmount = existingGoal.currentAmount.add(amount),
                updatedAt = clock.instant(),
            )
            val rowsUpdated = savingsGoalDao.updateSavingsGoal(updatedGoal.toEntity())

            if (rowsUpdated == 0) {
                DomainResult.Failure(
                    error = DomainError.NotFound(
                        entity = "SavingsGoal",
                        identifier = goalId,
                    ),
                )
            } else {
                DomainResult.Success(updatedGoal)
            }
        }
    }
}
