package com.grove.personalfinanceapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.grove.personalfinanceapp.data.local.entity.SavingsGoalEntity;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\t\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\u000f2\u0006\u0010\t\u001a\u00020\u0005H\'J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u0012"}, d2 = {"Lcom/grove/personalfinanceapp/data/local/dao/SavingsGoalDao;", "", "getSavingsGoalById", "Lcom/grove/personalfinanceapp/data/local/entity/SavingsGoalEntity;", "goalId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSavingsGoals", "", "userId", "insertSavingsGoal", "", "entity", "(Lcom/grove/personalfinanceapp/data/local/entity/SavingsGoalEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeSavingsGoals", "Lkotlinx/coroutines/flow/Flow;", "updateSavingsGoal", "", "data_debug"})
@androidx.room.Dao()
public abstract interface SavingsGoalDao {
    
    @androidx.room.Query(value = "\n        SELECT * FROM savings_goals\n        WHERE user_id = :userId\n        ORDER BY created_at_epoch_millis DESC\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.grove.personalfinanceapp.data.local.entity.SavingsGoalEntity>> observeSavingsGoals(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @androidx.room.Query(value = "\n        SELECT * FROM savings_goals\n        WHERE user_id = :userId\n        ORDER BY created_at_epoch_millis DESC\n        ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSavingsGoals(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.grove.personalfinanceapp.data.local.entity.SavingsGoalEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM savings_goals WHERE id = :goalId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSavingsGoalById(@org.jetbrains.annotations.NotNull()
    java.lang.String goalId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.grove.personalfinanceapp.data.local.entity.SavingsGoalEntity> $completion);
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertSavingsGoal(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.entity.SavingsGoalEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateSavingsGoal(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.entity.SavingsGoalEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}