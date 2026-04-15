package com.grove.personalfinanceapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.grove.personalfinanceapp.data.local.entity.BudgetEntity;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\t\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\u000f2\u0006\u0010\t\u001a\u00020\u0005H\'J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u0012"}, d2 = {"Lcom/grove/personalfinanceapp/data/local/dao/BudgetDao;", "", "getBudgetById", "Lcom/grove/personalfinanceapp/data/local/entity/BudgetEntity;", "budgetId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBudgets", "", "userId", "insertBudget", "", "entity", "(Lcom/grove/personalfinanceapp/data/local/entity/BudgetEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeBudgets", "Lkotlinx/coroutines/flow/Flow;", "updateBudget", "", "data_debug"})
@androidx.room.Dao()
public abstract interface BudgetDao {
    
    @androidx.room.Query(value = "\n        SELECT * FROM budgets\n        WHERE user_id = :userId\n        ORDER BY start_date_epoch_day DESC, created_at_epoch_millis DESC\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.grove.personalfinanceapp.data.local.entity.BudgetEntity>> observeBudgets(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @androidx.room.Query(value = "\n        SELECT * FROM budgets\n        WHERE user_id = :userId\n        ORDER BY start_date_epoch_day DESC, created_at_epoch_millis DESC\n        ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBudgets(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.grove.personalfinanceapp.data.local.entity.BudgetEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM budgets WHERE id = :budgetId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBudgetById(@org.jetbrains.annotations.NotNull()
    java.lang.String budgetId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.grove.personalfinanceapp.data.local.entity.BudgetEntity> $completion);
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertBudget(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.entity.BudgetEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateBudget(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.entity.BudgetEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}