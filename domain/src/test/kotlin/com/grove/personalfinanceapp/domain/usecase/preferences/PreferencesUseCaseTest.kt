package com.grove.personalfinanceapp.domain.usecase.preferences

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.DashboardPeriod
import com.grove.personalfinanceapp.domain.usecase.support.FakeUserPreferencesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.DayOfWeek

class PreferencesUseCaseTest {

    @Test
    fun `save user preferences normalizes the currency code`() = runBlocking {
        val repository = FakeUserPreferencesRepository()
        val useCase = SaveUserPreferences(repository)

        val result = useCase(
            SaveUserPreferences.Input(
                userId = " user-1 ",
                currencyCode = " usd ",
                defaultDashboardPeriod = DashboardPeriod.MONTH,
                showDashboardBalances = true,
                prioritizeSavingsGoals = true,
                weekStartsOn = DayOfWeek.MONDAY,
            ),
        )

        assertTrue(result is DomainResult.Success)
        val preferences = (result as DomainResult.Success).value
        assertEquals("user-1", preferences.userId)
        assertEquals("USD", preferences.currencyCode)
    }
}
