package com.grove.personalfinanceapp.domain.usecase.savingsgoal

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.model.SavingsGoal
import com.grove.personalfinanceapp.domain.model.SavingsGoalPriority
import com.grove.personalfinanceapp.domain.repository.SavingsGoalRepository
import com.grove.personalfinanceapp.domain.usecase.common.normalizedCurrencyCode
import com.grove.personalfinanceapp.domain.usecase.common.normalizedOptionalText
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import com.grove.personalfinanceapp.domain.usecase.common.notFoundFailure
import com.grove.personalfinanceapp.domain.usecase.common.runUseCase
import kotlinx.coroutines.flow.first
import java.math.BigDecimal
import java.time.Clock
import java.time.LocalDate

class UpdateSavingsGoal(
    private val savingsGoalRepository: SavingsGoalRepository,
    private val clock: Clock,
) {

    suspend operator fun invoke(input: Input): DomainResult<SavingsGoal> {
        return runUseCase {
            val userId = input.userId.normalizedText()
            val goalId = input.goalId.normalizedText()
            val name = input.name.normalizedText()
            val currencyCode = input.currencyCode.normalizedCurrencyCode()

            requireNotBlank(userId, "userId")
            requireNotBlank(goalId, "goalId")
            requireNotBlank(name, "name")

            val existingGoal = savingsGoalRepository.observeSavingsGoals(userId).first()
                .firstOrNull { goal -> goal.id == goalId }
                ?: return@runUseCase notFoundFailure(
                    entity = "SavingsGoal",
                    identifier = goalId,
                )

            val updatedGoal = existingGoal.copy(
                name = name,
                description = input.description.normalizedOptionalText(),
                targetAmount = input.targetAmount,
                currentAmount = input.currentAmount,
                currencyCode = currencyCode,
                targetDate = input.targetDate,
                priority = input.priority,
                updatedAt = clock.instant(),
            )

            savingsGoalRepository.updateSavingsGoal(updatedGoal)
        }
    }

    data class Input(
        val userId: String,
        val goalId: String,
        val name: String,
        val description: String?,
        val targetAmount: BigDecimal,
        val currentAmount: BigDecimal,
        val currencyCode: String,
        val targetDate: LocalDate?,
        val priority: SavingsGoalPriority,
    )
}
