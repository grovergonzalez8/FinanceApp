package com.grove.personalfinanceapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.grove.personalfinanceapp.data.local.entity.BudgetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {
    @Query(
        """
        SELECT * FROM budgets
        WHERE user_id = :userId
        ORDER BY start_date_epoch_day DESC, created_at_epoch_millis DESC
        """,
    )
    fun observeBudgets(userId: String): Flow<List<BudgetEntity>>

    @Query(
        """
        SELECT * FROM budgets
        WHERE user_id = :userId
        ORDER BY start_date_epoch_day DESC, created_at_epoch_millis DESC
        """,
    )
    suspend fun getBudgets(userId: String): List<BudgetEntity>

    @Query("SELECT * FROM budgets WHERE id = :budgetId LIMIT 1")
    suspend fun getBudgetById(budgetId: String): BudgetEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertBudget(entity: BudgetEntity)

    @Update
    suspend fun updateBudget(entity: BudgetEntity): Int
}
