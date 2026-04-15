package com.grove.personalfinanceapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.grove.personalfinanceapp.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query(
        """
        SELECT * FROM transactions
        WHERE user_id = :userId
        ORDER BY occurred_at_epoch_millis DESC, created_at_epoch_millis DESC
        """,
    )
    fun observeTransactions(userId: String): Flow<List<TransactionEntity>>

    @Query(
        """
        SELECT * FROM transactions
        WHERE user_id = :userId
        ORDER BY occurred_at_epoch_millis DESC, created_at_epoch_millis DESC
        """,
    )
    suspend fun getTransactions(userId: String): List<TransactionEntity>

    @Query("SELECT * FROM transactions WHERE id = :transactionId LIMIT 1")
    suspend fun getTransactionById(transactionId: String): TransactionEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTransaction(entity: TransactionEntity)

    @Update
    suspend fun updateTransaction(entity: TransactionEntity): Int

    @Query("DELETE FROM transactions WHERE id = :transactionId")
    suspend fun deleteTransactionById(transactionId: String): Int
}
