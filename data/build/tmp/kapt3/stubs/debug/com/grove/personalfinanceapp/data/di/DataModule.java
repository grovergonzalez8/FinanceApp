package com.grove.personalfinanceapp.data.di;

import android.content.Context;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.PreferenceDataStoreFactory;
import androidx.datastore.preferences.core.Preferences;
import androidx.room.Room;
import com.grove.personalfinanceapp.data.local.dao.BudgetDao;
import com.grove.personalfinanceapp.data.local.dao.CategoryDao;
import com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao;
import com.grove.personalfinanceapp.data.local.dao.TransactionDao;
import com.grove.personalfinanceapp.data.local.dao.UserProfileDao;
import com.grove.personalfinanceapp.data.local.database.FinanceDatabase;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import java.time.Clock;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0007J\b\u0010\f\u001a\u00020\rH\u0007J\u0012\u0010\u000e\u001a\u00020\t2\b\b\u0001\u0010\u000f\u001a\u00020\u0010H\u0007J\u0018\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\b\b\u0001\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\tH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/grove/personalfinanceapp/data/di/DataModule;", "", "()V", "DATABASE_NAME", "", "USER_PREFERENCES_FILE_NAME", "provideBudgetDao", "Lcom/grove/personalfinanceapp/data/local/dao/BudgetDao;", "database", "Lcom/grove/personalfinanceapp/data/local/database/FinanceDatabase;", "provideCategoryDao", "Lcom/grove/personalfinanceapp/data/local/dao/CategoryDao;", "provideClock", "Ljava/time/Clock;", "provideFinanceDatabase", "context", "Landroid/content/Context;", "providePreferencesDataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "provideSavingsGoalDao", "Lcom/grove/personalfinanceapp/data/local/dao/SavingsGoalDao;", "provideTransactionDao", "Lcom/grove/personalfinanceapp/data/local/dao/TransactionDao;", "provideUserProfileDao", "Lcom/grove/personalfinanceapp/data/local/dao/UserProfileDao;", "data_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DataModule {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DATABASE_NAME = "personal_finance.db";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String USER_PREFERENCES_FILE_NAME = "user_preferences.preferences_pb";
    @org.jetbrains.annotations.NotNull()
    public static final com.grove.personalfinanceapp.data.di.DataModule INSTANCE = null;
    
    private DataModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.grove.personalfinanceapp.data.local.database.FinanceDatabase provideFinanceDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.grove.personalfinanceapp.data.local.dao.UserProfileDao provideUserProfileDao(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.database.FinanceDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.grove.personalfinanceapp.data.local.dao.CategoryDao provideCategoryDao(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.database.FinanceDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.grove.personalfinanceapp.data.local.dao.TransactionDao provideTransactionDao(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.database.FinanceDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.grove.personalfinanceapp.data.local.dao.BudgetDao provideBudgetDao(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.database.FinanceDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao provideSavingsGoalDao(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.database.FinanceDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> providePreferencesDataStore(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final java.time.Clock provideClock() {
        return null;
    }
}