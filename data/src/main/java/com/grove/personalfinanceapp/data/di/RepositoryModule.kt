package com.grove.personalfinanceapp.data.di

import com.grove.personalfinanceapp.data.repository.BudgetRepositoryImpl
import com.grove.personalfinanceapp.data.repository.CategoryRepositoryImpl
import com.grove.personalfinanceapp.data.repository.DashboardRepositoryImpl
import com.grove.personalfinanceapp.data.repository.SavingsGoalRepositoryImpl
import com.grove.personalfinanceapp.data.repository.TransactionRepositoryImpl
import com.grove.personalfinanceapp.data.repository.UserPreferencesRepositoryImpl
import com.grove.personalfinanceapp.data.repository.UserProfileRepositoryImpl
import com.grove.personalfinanceapp.domain.repository.BudgetRepository
import com.grove.personalfinanceapp.domain.repository.CategoryRepository
import com.grove.personalfinanceapp.domain.repository.DashboardRepository
import com.grove.personalfinanceapp.domain.repository.SavingsGoalRepository
import com.grove.personalfinanceapp.domain.repository.TransactionRepository
import com.grove.personalfinanceapp.domain.repository.UserPreferencesRepository
import com.grove.personalfinanceapp.domain.repository.UserProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserProfileRepository(
        implementation: UserProfileRepositoryImpl,
    ): UserProfileRepository

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        implementation: CategoryRepositoryImpl,
    ): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindTransactionRepository(
        implementation: TransactionRepositoryImpl,
    ): TransactionRepository

    @Binds
    @Singleton
    abstract fun bindBudgetRepository(
        implementation: BudgetRepositoryImpl,
    ): BudgetRepository

    @Binds
    @Singleton
    abstract fun bindSavingsGoalRepository(
        implementation: SavingsGoalRepositoryImpl,
    ): SavingsGoalRepository

    @Binds
    @Singleton
    abstract fun bindDashboardRepository(
        implementation: DashboardRepositoryImpl,
    ): DashboardRepository

    @Binds
    @Singleton
    abstract fun bindUserPreferencesRepository(
        implementation: UserPreferencesRepositoryImpl,
    ): UserPreferencesRepository
}
