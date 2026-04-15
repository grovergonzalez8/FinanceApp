package com.grove.personalfinanceapp.data.repository

import com.grove.personalfinanceapp.data.common.runDataOperation
import com.grove.personalfinanceapp.data.local.dao.BudgetDao
import com.grove.personalfinanceapp.data.mapper.toDomain
import com.grove.personalfinanceapp.data.mapper.toEntity
import com.grove.personalfinanceapp.domain.common.DomainError
import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.Budget
import com.grove.personalfinanceapp.domain.repository.BudgetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BudgetRepositoryImpl @Inject constructor(
    private val budgetDao: BudgetDao,
) : BudgetRepository {

    override fun observeBudgets(userId: String): Flow<List<Budget>> {
        return budgetDao.observeBudgets(userId).map { entities ->
            entities.map { entity -> entity.toDomain() }
        }
    }

    override suspend fun createBudget(budget: Budget): DomainResult<Budget> {
        return runDataOperation(defaultMessage = "Unable to create budget.") {
            budgetDao.insertBudget(budget.toEntity())
            DomainResult.Success(budget)
        }
    }

    override suspend fun updateBudget(budget: Budget): DomainResult<Budget> {
        return runDataOperation(defaultMessage = "Unable to update budget.") {
            val rowsUpdated = budgetDao.updateBudget(budget.toEntity())
            if (rowsUpdated == 0) {
                DomainResult.Failure(
                    error = DomainError.NotFound(
                        entity = "Budget",
                        identifier = budget.id,
                    ),
                )
            } else {
                DomainResult.Success(budget)
            }
        }
    }
}
