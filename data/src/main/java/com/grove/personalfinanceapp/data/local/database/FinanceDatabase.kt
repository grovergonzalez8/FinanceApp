package com.grove.personalfinanceapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.grove.personalfinanceapp.data.local.dao.BudgetDao
import com.grove.personalfinanceapp.data.local.dao.CategoryDao
import com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao
import com.grove.personalfinanceapp.data.local.dao.TransactionDao
import com.grove.personalfinanceapp.data.local.dao.UserProfileDao
import com.grove.personalfinanceapp.data.local.entity.BudgetEntity
import com.grove.personalfinanceapp.data.local.entity.CategoryEntity
import com.grove.personalfinanceapp.data.local.entity.SavingsGoalEntity
import com.grove.personalfinanceapp.data.local.entity.TransactionEntity
import com.grove.personalfinanceapp.data.local.entity.UserProfileEntity

@Database(
    entities = [
        UserProfileEntity::class,
        CategoryEntity::class,
        TransactionEntity::class,
        BudgetEntity::class,
        SavingsGoalEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class FinanceDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao

    abstract fun categoryDao(): CategoryDao

    abstract fun transactionDao(): TransactionDao

    abstract fun budgetDao(): BudgetDao

    abstract fun savingsGoalDao(): SavingsGoalDao
}
