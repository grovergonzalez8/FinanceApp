package com.grove.personalfinanceapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "savings_goals",
    indices = [
        Index(value = ["user_id"]),
        Index(value = ["target_date_epoch_day"]),
    ],
)
data class SavingsGoalEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "target_amount")
    val targetAmount: String,
    @ColumnInfo(name = "current_amount")
    val currentAmount: String,
    @ColumnInfo(name = "currency_code")
    val currencyCode: String,
    @ColumnInfo(name = "target_date_epoch_day")
    val targetDateEpochDay: Long?,
    @ColumnInfo(name = "priority")
    val priority: String,
    @ColumnInfo(name = "created_at_epoch_millis")
    val createdAtEpochMillis: Long,
    @ColumnInfo(name = "updated_at_epoch_millis")
    val updatedAtEpochMillis: Long,
)
