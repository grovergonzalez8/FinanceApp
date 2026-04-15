package com.grove.personalfinanceapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.grove.personalfinanceapp.data.local.entity.SavingsGoalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SavingsGoalDao {
    @Query(
        """
        SELECT * FROM savings_goals
        WHERE user_id = :userId
        ORDER BY created_at_epoch_millis DESC
        """,
    )
    fun observeSavingsGoals(userId: String): Flow<List<SavingsGoalEntity>>

    @Query(
        """
        SELECT * FROM savings_goals
        WHERE user_id = :userId
        ORDER BY created_at_epoch_millis DESC
        """,
    )
    suspend fun getSavingsGoals(userId: String): List<SavingsGoalEntity>

    @Query("SELECT * FROM savings_goals WHERE id = :goalId LIMIT 1")
    suspend fun getSavingsGoalById(goalId: String): SavingsGoalEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertSavingsGoal(entity: SavingsGoalEntity)

    @Update
    suspend fun updateSavingsGoal(entity: SavingsGoalEntity): Int
}
