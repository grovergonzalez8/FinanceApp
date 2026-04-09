package com.grove.personalfinanceapp.domain.repository

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.SavingsGoal
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

interface SavingsGoalRepository {
    fun observeSavingsGoals(userId: String): Flow<List<SavingsGoal>>

    suspend fun createSavingsGoal(goal: SavingsGoal): DomainResult<SavingsGoal>

    suspend fun updateSavingsGoal(goal: SavingsGoal): DomainResult<SavingsGoal>

    suspend fun addContribution(
        goalId: String,
        amount: BigDecimal,
    ): DomainResult<SavingsGoal>
}
