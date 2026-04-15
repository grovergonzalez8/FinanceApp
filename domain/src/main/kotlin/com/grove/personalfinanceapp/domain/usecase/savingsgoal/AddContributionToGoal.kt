package com.grove.personalfinanceapp.domain.usecase.savingsgoal

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.common.requirePositive
import com.grove.personalfinanceapp.domain.model.SavingsGoal
import com.grove.personalfinanceapp.domain.repository.SavingsGoalRepository
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import com.grove.personalfinanceapp.domain.usecase.common.notFoundFailure
import com.grove.personalfinanceapp.domain.usecase.common.runUseCase
import kotlinx.coroutines.flow.first
import java.math.BigDecimal

class AddContributionToGoal(
    private val savingsGoalRepository: SavingsGoalRepository,
) {

    suspend operator fun invoke(input: Input): DomainResult<SavingsGoal> {
        return runUseCase {
            val userId = input.userId.normalizedText()
            val goalId = input.goalId.normalizedText()

            requireNotBlank(userId, "userId")
            requireNotBlank(goalId, "goalId")
            requirePositive(input.amount, "amount")

            val goalExists = savingsGoalRepository.observeSavingsGoals(userId).first()
                .any { goal -> goal.id == goalId }

            if (!goalExists) {
                return@runUseCase notFoundFailure(
                    entity = "SavingsGoal",
                    identifier = goalId,
                )
            }

            savingsGoalRepository.addContribution(goalId = goalId, amount = input.amount)
        }
    }

    data class Input(
        val userId: String,
        val goalId: String,
        val amount: BigDecimal,
    )
}
