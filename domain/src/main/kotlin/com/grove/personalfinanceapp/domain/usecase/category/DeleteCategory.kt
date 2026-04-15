package com.grove.personalfinanceapp.domain.usecase.category

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.repository.BudgetRepository
import com.grove.personalfinanceapp.domain.repository.CategoryRepository
import com.grove.personalfinanceapp.domain.repository.TransactionRepository
import com.grove.personalfinanceapp.domain.usecase.common.conflictFailure
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import com.grove.personalfinanceapp.domain.usecase.common.notFoundFailure
import com.grove.personalfinanceapp.domain.usecase.common.runUseCase
import kotlinx.coroutines.flow.first

class DeleteCategory(
    private val categoryRepository: CategoryRepository,
    private val transactionRepository: TransactionRepository,
    private val budgetRepository: BudgetRepository,
) {

    suspend operator fun invoke(input: Input): DomainResult<Unit> {
        return runUseCase {
            val userId = input.userId.normalizedText()
            val categoryId = input.categoryId.normalizedText()

            requireNotBlank(userId, "userId")
            requireNotBlank(categoryId, "categoryId")

            val existingCategory = categoryRepository.observeCategories(userId).first()
                .firstOrNull { category -> category.id == categoryId }
                ?: return@runUseCase notFoundFailure(
                    entity = "Category",
                    identifier = categoryId,
                )

            if (existingCategory.isDefault) {
                return@runUseCase conflictFailure("Default categories cannot be deleted.")
            }

            val isUsedByTransactions = transactionRepository.observeTransactions(userId).first()
                .any { transaction -> transaction.categoryId == categoryId }
            if (isUsedByTransactions) {
                return@runUseCase conflictFailure(
                    "Categories linked to transactions cannot be deleted.",
                )
            }

            val isUsedByBudgets = budgetRepository.observeBudgets(userId).first()
                .any { budget -> budget.categoryId == categoryId }
            if (isUsedByBudgets) {
                return@runUseCase conflictFailure(
                    "Categories linked to budgets cannot be deleted.",
                )
            }

            categoryRepository.deleteCategory(categoryId)
        }
    }

    data class Input(
        val userId: String,
        val categoryId: String,
    )
}
