package com.grove.personalfinanceapp.domain.usecase.budget

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.common.requirePositive
import com.grove.personalfinanceapp.domain.model.Budget
import com.grove.personalfinanceapp.domain.model.BudgetPeriod
import com.grove.personalfinanceapp.domain.model.DateRange
import com.grove.personalfinanceapp.domain.repository.BudgetRepository
import com.grove.personalfinanceapp.domain.repository.CategoryRepository
import com.grove.personalfinanceapp.domain.usecase.common.IdGenerator
import com.grove.personalfinanceapp.domain.usecase.common.UuidGenerator
import com.grove.personalfinanceapp.domain.usecase.common.normalizedCurrencyCode
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import com.grove.personalfinanceapp.domain.usecase.common.notFoundFailure
import com.grove.personalfinanceapp.domain.usecase.common.runUseCase
import kotlinx.coroutines.flow.first
import java.math.BigDecimal
import java.time.Clock

class CreateBudget(
    private val budgetRepository: BudgetRepository,
    private val categoryRepository: CategoryRepository,
    private val clock: Clock,
    private val idGenerator: IdGenerator = UuidGenerator,
) {

    suspend operator fun invoke(input: Input): DomainResult<Budget> {
        return runUseCase {
            val userId = input.userId.normalizedText()
            val categoryId = input.categoryId.normalizedText()
            val currencyCode = input.currencyCode.normalizedCurrencyCode()

            requireNotBlank(userId, "userId")
            requireNotBlank(categoryId, "categoryId")
            requirePositive(input.limitAmount, "limitAmount")

            val category = categoryRepository.observeCategories(userId).first()
                .firstOrNull { category -> category.id == categoryId }
                ?: return@runUseCase notFoundFailure(
                    entity = "Category",
                    identifier = categoryId,
                )

            validateBudgetCategory(category)?.let { failure ->
                return@runUseCase failure
            }
            validateBudgetRange(input.period, input.range)?.let { failure ->
                return@runUseCase failure
            }

            val now = clock.instant()
            val budget = Budget(
                id = idGenerator.generate(),
                userId = userId,
                categoryId = categoryId,
                limitAmount = input.limitAmount,
                currencyCode = currencyCode,
                period = input.period,
                range = input.range,
                createdAt = now,
                updatedAt = now,
            )

            budgetRepository.createBudget(budget)
        }
    }

    data class Input(
        val userId: String,
        val categoryId: String,
        val limitAmount: BigDecimal,
        val currencyCode: String,
        val period: BudgetPeriod,
        val range: DateRange,
    )
}
