package com.grove.personalfinanceapp.domain.usecase.budget

import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.model.Budget
import com.grove.personalfinanceapp.domain.repository.BudgetRepository
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import kotlinx.coroutines.flow.Flow

class GetBudgets(
    private val budgetRepository: BudgetRepository,
) {

    operator fun invoke(userId: String): Flow<List<Budget>> {
        val normalizedUserId = userId.normalizedText()
        requireNotBlank(normalizedUserId, "userId")

        return budgetRepository.observeBudgets(normalizedUserId)
    }
}
