package com.grove.personalfinanceapp.domain.usecase.savingsgoal

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.model.SavingsGoal
import com.grove.personalfinanceapp.domain.model.SavingsGoalPriority
import com.grove.personalfinanceapp.domain.repository.SavingsGoalRepository
import com.grove.personalfinanceapp.domain.usecase.common.IdGenerator
import com.grove.personalfinanceapp.domain.usecase.common.UuidGenerator
import com.grove.personalfinanceapp.domain.usecase.common.normalizedCurrencyCode
import com.grove.personalfinanceapp.domain.usecase.common.normalizedOptionalText
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import com.grove.personalfinanceapp.domain.usecase.common.runUseCase
import java.math.BigDecimal
import java.time.Clock
import java.time.LocalDate

class CreateSavingsGoal(
    private val savingsGoalRepository: SavingsGoalRepository,
    private val clock: Clock,
    private val idGenerator: IdGenerator = UuidGenerator,
) {

    suspend operator fun invoke(input: Input): DomainResult<SavingsGoal> {
        return runUseCase {
            val userId = input.userId.normalizedText()
            val name = input.name.normalizedText()
            val currencyCode = input.currencyCode.normalizedCurrencyCode()

            requireNotBlank(userId, "userId")
            requireNotBlank(name, "name")

            val now = clock.instant()
            val goal = SavingsGoal(
                id = idGenerator.generate(),
                userId = userId,
                name = name,
                description = input.description.normalizedOptionalText(),
                targetAmount = input.targetAmount,
                currentAmount = input.currentAmount,
                currencyCode = currencyCode,
                targetDate = input.targetDate,
                priority = input.priority,
                createdAt = now,
                updatedAt = now,
            )

            savingsGoalRepository.createSavingsGoal(goal)
        }
    }

    data class Input(
        val userId: String,
        val name: String,
        val description: String?,
        val targetAmount: BigDecimal,
        val currentAmount: BigDecimal = BigDecimal.ZERO,
        val currencyCode: String,
        val targetDate: LocalDate?,
        val priority: SavingsGoalPriority,
    )
}
