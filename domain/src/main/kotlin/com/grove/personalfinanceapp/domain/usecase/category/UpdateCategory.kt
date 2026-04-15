package com.grove.personalfinanceapp.domain.usecase.category

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.model.Category
import com.grove.personalfinanceapp.domain.model.TransactionType
import com.grove.personalfinanceapp.domain.repository.BudgetRepository
import com.grove.personalfinanceapp.domain.repository.CategoryRepository
import com.grove.personalfinanceapp.domain.repository.TransactionRepository
import com.grove.personalfinanceapp.domain.usecase.common.conflictFailure
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import com.grove.personalfinanceapp.domain.usecase.common.notFoundFailure
import com.grove.personalfinanceapp.domain.usecase.common.runUseCase
import kotlinx.coroutines.flow.first
import java.time.Clock

class UpdateCategory(
    private val categoryRepository: CategoryRepository,
    private val transactionRepository: TransactionRepository,
    private val budgetRepository: BudgetRepository,
    private val clock: Clock,
) {

    suspend operator fun invoke(input: Input): DomainResult<Category> {
        return runUseCase {
            val userId = input.userId.normalizedText()
            val categoryId = input.categoryId.normalizedText()
            val name = input.name.normalizedText()

            requireNotBlank(userId, "userId")
            requireNotBlank(categoryId, "categoryId")
            requireNotBlank(name, "name")

            val existingCategory = categoryRepository.observeCategories(userId).first()
                .firstOrNull { category -> category.id == categoryId }
                ?: return@runUseCase notFoundFailure(
                    entity = "Category",
                    identifier = categoryId,
                )

            if (existingCategory.type != input.type) {
                val hasTransactions = transactionRepository.observeTransactions(userId).first()
                    .any { transaction -> transaction.categoryId == categoryId }
                val hasBudgets = budgetRepository.observeBudgets(userId).first()
                    .any { budget -> budget.categoryId == categoryId }

                if (hasTransactions || hasBudgets) {
                    return@runUseCase conflictFailure(
                        "Category type cannot change while it is referenced by transactions or budgets.",
                    )
                }
            }

            val updatedCategory = existingCategory.copy(
                name = name,
                type = input.type,
                updatedAt = clock.instant(),
            )

            categoryRepository.updateCategory(updatedCategory)
        }
    }

    data class Input(
        val userId: String,
        val categoryId: String,
        val name: String,
        val type: TransactionType,
    )
}
