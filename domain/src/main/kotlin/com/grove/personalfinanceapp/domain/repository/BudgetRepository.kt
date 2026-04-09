package com.grove.personalfinanceapp.domain.repository

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.Budget
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {
    fun observeBudgets(userId: String): Flow<List<Budget>>

    suspend fun createBudget(budget: Budget): DomainResult<Budget>

    suspend fun updateBudget(budget: Budget): DomainResult<Budget>
}
