package com.grove.personalfinanceapp.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.grove.personalfinanceapp.domain.model.DashboardPeriod
import com.grove.personalfinanceapp.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.time.DayOfWeek
import javax.inject.Inject

class UserPreferencesDataStoreSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {

    fun observeUserPreferences(userId: String): Flow<UserPreferences?> {
        return dataStore.data
            .catch { throwable ->
                if (throwable is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw throwable
                }
            }
            .map { preferences -> preferences.toUserPreferences(userId) }
    }

    suspend fun getUserPreferences(userId: String): UserPreferences? {
        return observeUserPreferences(userId).first()
    }

    suspend fun saveUserPreferences(preferences: UserPreferences) {
        dataStore.edit { values ->
            values[currencyCodeKey(preferences.userId)] = preferences.currencyCode
            values[dashboardPeriodKey(preferences.userId)] = preferences.defaultDashboardPeriod.name
            values[showBalancesKey(preferences.userId)] = preferences.showDashboardBalances
            values[prioritizeSavingsGoalsKey(preferences.userId)] = preferences.prioritizeSavingsGoals
            values[weekStartsOnKey(preferences.userId)] = preferences.weekStartsOn.value
        }
    }

    private fun Preferences.toUserPreferences(userId: String): UserPreferences? {
        val currencyCode = this[currencyCodeKey(userId)] ?: return null
        val dashboardPeriod = this[dashboardPeriodKey(userId)] ?: return null
        val showBalances = this[showBalancesKey(userId)] ?: return null
        val prioritizeSavingsGoals = this[prioritizeSavingsGoalsKey(userId)] ?: return null
        val weekStartsOn = this[weekStartsOnKey(userId)] ?: return null

        return UserPreferences(
            userId = userId,
            currencyCode = currencyCode,
            defaultDashboardPeriod = DashboardPeriod.valueOf(dashboardPeriod),
            showDashboardBalances = showBalances,
            prioritizeSavingsGoals = prioritizeSavingsGoals,
            weekStartsOn = DayOfWeek.of(weekStartsOn),
        )
    }

    private fun currencyCodeKey(userId: String) = stringPreferencesKey("${userId}_currency_code")

    private fun dashboardPeriodKey(userId: String) =
        stringPreferencesKey("${userId}_default_dashboard_period")

    private fun showBalancesKey(userId: String) =
        booleanPreferencesKey("${userId}_show_dashboard_balances")

    private fun prioritizeSavingsGoalsKey(userId: String) =
        booleanPreferencesKey("${userId}_prioritize_savings_goals")

    private fun weekStartsOnKey(userId: String) =
        intPreferencesKey("${userId}_week_starts_on")
}
