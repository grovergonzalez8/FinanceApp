package com.grove.personalfinanceapp.domain.usecase.support

import com.grove.personalfinanceapp.domain.common.DomainError
import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.Budget
import com.grove.personalfinanceapp.domain.model.DashboardPeriod
import com.grove.personalfinanceapp.domain.model.DashboardSummary
import com.grove.personalfinanceapp.domain.model.FinancialInsight
import com.grove.personalfinanceapp.domain.model.FinancialTransaction
import com.grove.personalfinanceapp.domain.model.SavingsGoal
import com.grove.personalfinanceapp.domain.model.UserPreferences
import com.grove.personalfinanceapp.domain.model.UserProfile
import com.grove.personalfinanceapp.domain.model.Category
import com.grove.personalfinanceapp.domain.repository.BudgetRepository
import com.grove.personalfinanceapp.domain.repository.CategoryRepository
import com.grove.personalfinanceapp.domain.repository.DashboardRepository
import com.grove.personalfinanceapp.domain.repository.SavingsGoalRepository
import com.grove.personalfinanceapp.domain.repository.TransactionRepository
import com.grove.personalfinanceapp.domain.repository.UserPreferencesRepository
import com.grove.personalfinanceapp.domain.repository.UserProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal

class FakeUserProfileRepository(
    initialProfile: UserProfile? = null,
) : UserProfileRepository {
    private val profileState = MutableStateFlow(initialProfile)

    override fun observeUserProfile(userId: String): Flow<UserProfile?> {
        return profileState.map { profile -> profile?.takeIf { it.id == userId } }
    }

    override suspend fun createUserProfile(profile: UserProfile): DomainResult<UserProfile> {
        return if (profileState.value == null) {
            profileState.value = profile
            DomainResult.Success(profile)
        } else {
            DomainResult.Failure(
                error = DomainError.Conflict("User profile already exists."),
            )
        }
    }

    override suspend fun updateUserProfile(profile: UserProfile): DomainResult<UserProfile> {
        return if (profileState.value?.id == profile.id) {
            profileState.value = profile
            DomainResult.Success(profile)
        } else {
            DomainResult.Failure(
                error = DomainError.NotFound(
                    entity = "UserProfile",
                    identifier = profile.id,
                ),
            )
        }
    }
}

class FakeTransactionRepository(
    initialTransactions: List<FinancialTransaction> = emptyList(),
) : TransactionRepository {
    private val transactionState = MutableStateFlow(initialTransactions)

    override fun observeTransactions(userId: String): Flow<List<FinancialTransaction>> {
        return transactionState.map { transactions ->
            transactions.filter { transaction -> transaction.userId == userId }
        }
    }

    override suspend fun addTransaction(
        transaction: FinancialTransaction,
    ): DomainResult<FinancialTransaction> {
        transactionState.value += transaction
        return DomainResult.Success(transaction)
    }

    override suspend fun updateTransaction(
        transaction: FinancialTransaction,
    ): DomainResult<FinancialTransaction> {
        val existingIndex = transactionState.value.indexOfFirst { item -> item.id == transaction.id }
        return if (existingIndex >= 0) {
            transactionState.value = transactionState.value.toMutableList().also { transactions ->
                transactions[existingIndex] = transaction
            }
            DomainResult.Success(transaction)
        } else {
            DomainResult.Failure(
                error = DomainError.NotFound(
                    entity = "FinancialTransaction",
                    identifier = transaction.id,
                ),
            )
        }
    }

    override suspend fun deleteTransaction(transactionId: String): DomainResult<Unit> {
        val existingTransactions = transactionState.value
        return if (existingTransactions.any { transaction -> transaction.id == transactionId }) {
            transactionState.value = existingTransactions.filterNot { transaction ->
                transaction.id == transactionId
            }
            DomainResult.Success(Unit)
        } else {
            DomainResult.Failure(
                error = DomainError.NotFound(
                    entity = "FinancialTransaction",
                    identifier = transactionId,
                ),
            )
        }
    }
}

class FakeCategoryRepository(
    initialCategories: List<Category> = emptyList(),
) : CategoryRepository {
    private val categoryState = MutableStateFlow(initialCategories)

    override fun observeCategories(userId: String): Flow<List<Category>> {
        return categoryState.map { categories ->
            categories.filter { category -> category.userId == userId }
        }
    }

    override suspend fun createCategory(category: Category): DomainResult<Category> {
        categoryState.value += category
        return DomainResult.Success(category)
    }

    override suspend fun updateCategory(category: Category): DomainResult<Category> {
        val existingIndex = categoryState.value.indexOfFirst { item -> item.id == category.id }
        return if (existingIndex >= 0) {
            categoryState.value = categoryState.value.toMutableList().also { categories ->
                categories[existingIndex] = category
            }
            DomainResult.Success(category)
        } else {
            DomainResult.Failure(
                error = DomainError.NotFound(
                    entity = "Category",
                    identifier = category.id,
                ),
            )
        }
    }

    override suspend fun deleteCategory(categoryId: String): DomainResult<Unit> {
        val existingCategories = categoryState.value
        return if (existingCategories.any { category -> category.id == categoryId }) {
            categoryState.value = existingCategories.filterNot { category ->
                category.id == categoryId
            }
            DomainResult.Success(Unit)
        } else {
            DomainResult.Failure(
                error = DomainError.NotFound(
                    entity = "Category",
                    identifier = categoryId,
                ),
            )
        }
    }
}

class FakeBudgetRepository(
    initialBudgets: List<Budget> = emptyList(),
) : BudgetRepository {
    private val budgetState = MutableStateFlow(initialBudgets)

    override fun observeBudgets(userId: String): Flow<List<Budget>> {
        return budgetState.map { budgets ->
            budgets.filter { budget -> budget.userId == userId }
        }
    }

    override suspend fun createBudget(budget: Budget): DomainResult<Budget> {
        budgetState.value += budget
        return DomainResult.Success(budget)
    }

    override suspend fun updateBudget(budget: Budget): DomainResult<Budget> {
        val existingIndex = budgetState.value.indexOfFirst { item -> item.id == budget.id }
        return if (existingIndex >= 0) {
            budgetState.value = budgetState.value.toMutableList().also { budgets ->
                budgets[existingIndex] = budget
            }
            DomainResult.Success(budget)
        } else {
            DomainResult.Failure(
                error = DomainError.NotFound(
                    entity = "Budget",
                    identifier = budget.id,
                ),
            )
        }
    }
}

class FakeSavingsGoalRepository(
    initialGoals: List<SavingsGoal> = emptyList(),
) : SavingsGoalRepository {
    private val goalState = MutableStateFlow(initialGoals)

    override fun observeSavingsGoals(userId: String): Flow<List<SavingsGoal>> {
        return goalState.map { goals ->
            goals.filter { goal -> goal.userId == userId }
        }
    }

    override suspend fun createSavingsGoal(goal: SavingsGoal): DomainResult<SavingsGoal> {
        goalState.value += goal
        return DomainResult.Success(goal)
    }

    override suspend fun updateSavingsGoal(goal: SavingsGoal): DomainResult<SavingsGoal> {
        val existingIndex = goalState.value.indexOfFirst { item -> item.id == goal.id }
        return if (existingIndex >= 0) {
            goalState.value = goalState.value.toMutableList().also { goals ->
                goals[existingIndex] = goal
            }
            DomainResult.Success(goal)
        } else {
            DomainResult.Failure(
                error = DomainError.NotFound(
                    entity = "SavingsGoal",
                    identifier = goal.id,
                ),
            )
        }
    }

    override suspend fun addContribution(
        goalId: String,
        amount: BigDecimal,
    ): DomainResult<SavingsGoal> {
        val existingGoal = goalState.value.firstOrNull { goal -> goal.id == goalId }
            ?: return DomainResult.Failure(
                error = DomainError.NotFound(
                    entity = "SavingsGoal",
                    identifier = goalId,
                ),
            )

        if (amount <= BigDecimal.ZERO) {
            return DomainResult.Failure(
                error = DomainError.Validation(
                    message = "amount must be greater than zero.",
                    field = "amount",
                ),
            )
        }

        val updatedGoal = existingGoal.copy(
            currentAmount = existingGoal.currentAmount.add(amount),
        )
        goalState.value = goalState.value.map { goal ->
            if (goal.id == goalId) updatedGoal else goal
        }

        return DomainResult.Success(updatedGoal)
    }
}

class FakeUserPreferencesRepository(
    initialPreferences: UserPreferences? = null,
) : UserPreferencesRepository {
    private val preferenceState = MutableStateFlow(initialPreferences)

    override fun observeUserPreferences(userId: String): Flow<UserPreferences?> {
        return preferenceState.map { preferences ->
            preferences?.takeIf { it.userId == userId }
        }
    }

    override suspend fun saveUserPreferences(
        preferences: UserPreferences,
    ): DomainResult<UserPreferences> {
        preferenceState.value = preferences
        return DomainResult.Success(preferences)
    }
}

class FakeDashboardRepository(
    private val summary: DashboardSummary,
    private val insights: List<FinancialInsight> = emptyList(),
) : DashboardRepository {

    override fun observeDashboardSummary(
        userId: String,
        period: DashboardPeriod,
    ): Flow<DashboardSummary> {
        return MutableStateFlow(summary)
    }

    override suspend fun generateFinancialInsights(
        userId: String,
        period: DashboardPeriod,
    ): DomainResult<List<FinancialInsight>> {
        return DomainResult.Success(insights)
    }
}
