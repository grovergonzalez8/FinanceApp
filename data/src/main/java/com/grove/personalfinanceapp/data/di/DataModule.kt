package com.grove.personalfinanceapp.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.grove.personalfinanceapp.data.local.dao.BudgetDao
import com.grove.personalfinanceapp.data.local.dao.CategoryDao
import com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao
import com.grove.personalfinanceapp.data.local.dao.TransactionDao
import com.grove.personalfinanceapp.data.local.dao.UserProfileDao
import com.grove.personalfinanceapp.data.local.database.FinanceDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.time.Clock
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideFinanceDatabase(
        @ApplicationContext context: Context,
    ): FinanceDatabase {
        return Room.databaseBuilder(
            context,
            FinanceDatabase::class.java,
            DATABASE_NAME,
        ).build()
    }

    @Provides
    fun provideUserProfileDao(database: FinanceDatabase): UserProfileDao = database.userProfileDao()

    @Provides
    fun provideCategoryDao(database: FinanceDatabase): CategoryDao = database.categoryDao()

    @Provides
    fun provideTransactionDao(database: FinanceDatabase): TransactionDao = database.transactionDao()

    @Provides
    fun provideBudgetDao(database: FinanceDatabase): BudgetDao = database.budgetDao()

    @Provides
    fun provideSavingsGoalDao(database: FinanceDatabase): SavingsGoalDao = database.savingsGoalDao()

    @Provides
    @Singleton
    fun providePreferencesDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(USER_PREFERENCES_FILE_NAME)
            },
        )
    }

    @Provides
    @Singleton
    fun provideClock(): Clock = Clock.systemDefaultZone()

    private const val DATABASE_NAME = "personal_finance.db"
    private const val USER_PREFERENCES_FILE_NAME = "user_preferences.preferences_pb"
}
