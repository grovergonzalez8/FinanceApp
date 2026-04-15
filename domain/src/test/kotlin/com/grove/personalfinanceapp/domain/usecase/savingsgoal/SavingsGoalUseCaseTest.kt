package com.grove.personalfinanceapp.domain.usecase.savingsgoal

import com.grove.personalfinanceapp.domain.common.DomainError
import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.SavingsGoalPriority
import com.grove.personalfinanceapp.domain.usecase.common.IdGenerator
import com.grove.personalfinanceapp.domain.usecase.support.FakeSavingsGoalRepository
import com.grove.personalfinanceapp.domain.usecase.support.savingsGoal
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

class SavingsGoalUseCaseTest {

    private val clock = Clock.fixed(
        Instant.parse("2026-04-14T12:00:00Z"),
        ZoneOffset.UTC,
    )

    @Test
    fun `add contribution returns not found when the goal does not belong to the user`() = runBlocking {
        val repository = FakeSavingsGoalRepository(
            initialGoals = listOf(
                savingsGoal(userId = "user-2"),
            ),
        )
        val useCase = AddContributionToGoal(repository)

        val result = useCase(
            AddContributionToGoal.Input(
                userId = "user-1",
                goalId = "goal-1",
                amount = BigDecimal("100.00"),
            ),
        )

        assertTrue(result is DomainResult.Failure)
        val error = (result as DomainResult.Failure).error as DomainError.NotFound
        assertEquals("SavingsGoal", error.entity)
        assertEquals("goal-1", error.identifier)
    }

    @Test
    fun `create savings goal normalizes blank descriptions and uses the default current amount`() = runBlocking {
        val repository = FakeSavingsGoalRepository()
        val useCase = CreateSavingsGoal(
            savingsGoalRepository = repository,
            clock = clock,
            idGenerator = IdGenerator { "goal-9" },
        )

        val result = useCase(
            CreateSavingsGoal.Input(
                userId = "user-1",
                name = " Vacation ",
                description = "   ",
                targetAmount = BigDecimal("2500.00"),
                currencyCode = " usd ",
                targetDate = null,
                priority = SavingsGoalPriority.MEDIUM,
            ),
        )

        assertTrue(result is DomainResult.Success)
        val goal = (result as DomainResult.Success).value
        assertEquals("goal-9", goal.id)
        assertEquals("Vacation", goal.name)
        assertNull(goal.description)
        assertEquals(BigDecimal.ZERO, goal.currentAmount)
        assertEquals("USD", goal.currencyCode)
    }
}
