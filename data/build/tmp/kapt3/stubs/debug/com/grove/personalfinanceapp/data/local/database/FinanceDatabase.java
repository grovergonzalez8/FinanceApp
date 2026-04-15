package com.grove.personalfinanceapp.data.local.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.grove.personalfinanceapp.data.local.dao.BudgetDao;
import com.grove.personalfinanceapp.data.local.dao.CategoryDao;
import com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao;
import com.grove.personalfinanceapp.data.local.dao.TransactionDao;
import com.grove.personalfinanceapp.data.local.dao.UserProfileDao;
import com.grove.personalfinanceapp.data.local.entity.BudgetEntity;
import com.grove.personalfinanceapp.data.local.entity.CategoryEntity;
import com.grove.personalfinanceapp.data.local.entity.SavingsGoalEntity;
import com.grove.personalfinanceapp.data.local.entity.TransactionEntity;
import com.grove.personalfinanceapp.data.local.entity.UserProfileEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\r"}, d2 = {"Lcom/grove/personalfinanceapp/data/local/database/FinanceDatabase;", "Landroidx/room/RoomDatabase;", "()V", "budgetDao", "Lcom/grove/personalfinanceapp/data/local/dao/BudgetDao;", "categoryDao", "Lcom/grove/personalfinanceapp/data/local/dao/CategoryDao;", "savingsGoalDao", "Lcom/grove/personalfinanceapp/data/local/dao/SavingsGoalDao;", "transactionDao", "Lcom/grove/personalfinanceapp/data/local/dao/TransactionDao;", "userProfileDao", "Lcom/grove/personalfinanceapp/data/local/dao/UserProfileDao;", "data_debug"})
@androidx.room.Database(entities = {com.grove.personalfinanceapp.data.local.entity.UserProfileEntity.class, com.grove.personalfinanceapp.data.local.entity.CategoryEntity.class, com.grove.personalfinanceapp.data.local.entity.TransactionEntity.class, com.grove.personalfinanceapp.data.local.entity.BudgetEntity.class, com.grove.personalfinanceapp.data.local.entity.SavingsGoalEntity.class}, version = 1, exportSchema = false)
public abstract class FinanceDatabase extends androidx.room.RoomDatabase {
    
    public FinanceDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.grove.personalfinanceapp.data.local.dao.UserProfileDao userProfileDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.grove.personalfinanceapp.data.local.dao.CategoryDao categoryDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.grove.personalfinanceapp.data.local.dao.TransactionDao transactionDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.grove.personalfinanceapp.data.local.dao.BudgetDao budgetDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao savingsGoalDao();
}