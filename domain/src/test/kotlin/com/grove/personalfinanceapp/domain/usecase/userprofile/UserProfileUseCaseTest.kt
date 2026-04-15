package com.grove.personalfinanceapp.domain.usecase.userprofile

import com.grove.personalfinanceapp.domain.common.DomainError
import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.usecase.support.FakeUserProfileRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

class UserProfileUseCaseTest {

    private val clock = Clock.fixed(
        Instant.parse("2026-04-14T12:00:00Z"),
        ZoneOffset.UTC,
    )

    @Test
    fun `create user profile uses the user id as the profile id`() = runBlocking {
        val repository = FakeUserProfileRepository()
        val useCase = CreateUserProfile(
            userProfileRepository = repository,
            clock = clock,
        )

        val result = useCase(
            CreateUserProfile.Input(
                userId = " user-1 ",
                displayName = " Alex ",
                monthlyIncome = BigDecimal("4200.00"),
                currencyCode = " usd ",
            ),
        )

        assertTrue(result is DomainResult.Success)
        val profile = (result as DomainResult.Success).value
        assertEquals("user-1", profile.id)
        assertEquals("Alex", profile.displayName)
        assertEquals("USD", profile.currencyCode)
        assertEquals(clock.instant(), profile.createdAt)
        assertEquals(clock.instant(), profile.updatedAt)
    }

    @Test
    fun `update user profile returns not found when the profile does not exist`() = runBlocking {
        val repository = FakeUserProfileRepository()
        val useCase = UpdateUserProfile(
            userProfileRepository = repository,
            clock = clock,
        )

        val result = useCase(
            UpdateUserProfile.Input(
                userId = "user-1",
                displayName = "Alex",
                monthlyIncome = BigDecimal("3000.00"),
                currencyCode = "USD",
            ),
        )

        assertTrue(result is DomainResult.Failure)
        val error = (result as DomainResult.Failure).error as DomainError.NotFound
        assertEquals("UserProfile", error.entity)
        assertEquals("user-1", error.identifier)
    }
}
