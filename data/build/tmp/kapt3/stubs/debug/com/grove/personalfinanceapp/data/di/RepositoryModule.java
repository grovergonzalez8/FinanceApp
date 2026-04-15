package com.grove.personalfinanceapp.data.di;

import com.grove.personalfinanceapp.data.repository.BudgetRepositoryImpl;
import com.grove.personalfinanceapp.data.repository.CategoryRepositoryImpl;
import com.grove.personalfinanceapp.data.repository.DashboardRepositoryImpl;
import com.grove.personalfinanceapp.data.repository.SavingsGoalRepositoryImpl;
import com.grove.personalfinanceapp.data.repository.TransactionRepositoryImpl;
import com.grove.personalfinanceapp.data.repository.UserPreferencesRepositoryImpl;
import com.grove.personalfinanceapp.data.repository.UserProfileRepositoryImpl;
import com.grove.personalfinanceapp.domain.repository.BudgetRepository;
import com.grove.personalfinanceapp.domain.repository.CategoryRepository;
import com.grove.personalfinanceapp.domain.repository.DashboardRepository;
import com.grove.personalfinanceapp.domain.repository.SavingsGoalRepository;
import com.grove.personalfinanceapp.domain.repository.TransactionRepository;
import com.grove.personalfinanceapp.domain.repository.UserPreferencesRepository;
import com.grove.personalfinanceapp.domain.repository.UserProfileRepository;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\fH\'J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000fH\'J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0012H\'J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0015H\'J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0018H\'\u00a8\u0006\u0019"}, d2 = {"Lcom/grove/personalfinanceapp/data/di/RepositoryModule;", "", "()V", "bindBudgetRepository", "Lcom/grove/personalfinanceapp/domain/repository/BudgetRepository;", "implementation", "Lcom/grove/personalfinanceapp/data/repository/BudgetRepositoryImpl;", "bindCategoryRepository", "Lcom/grove/personalfinanceapp/domain/repository/CategoryRepository;", "Lcom/grove/personalfinanceapp/data/repository/CategoryRepositoryImpl;", "bindDashboardRepository", "Lcom/grove/personalfinanceapp/domain/repository/DashboardRepository;", "Lcom/grove/personalfinanceapp/data/repository/DashboardRepositoryImpl;", "bindSavingsGoalRepository", "Lcom/grove/personalfinanceapp/domain/repository/SavingsGoalRepository;", "Lcom/grove/personalfinanceapp/data/repository/SavingsGoalRepositoryImpl;", "bindTransactionRepository", "Lcom/grove/personalfinanceapp/domain/repository/TransactionRepository;", "Lcom/grove/personalfinanceapp/data/repository/TransactionRepositoryImpl;", "bindUserPreferencesRepository", "Lcom/grove/personalfinanceapp/domain/repository/UserPreferencesRepository;", "Lcom/grove/personalfinanceapp/data/repository/UserPreferencesRepositoryImpl;", "bindUserProfileRepository", "Lcom/grove/personalfinanceapp/domain/repository/UserProfileRepository;", "Lcom/grove/personalfinanceapp/data/repository/UserProfileRepositoryImpl;", "data_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class RepositoryModule {
    
    public RepositoryModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.grove.personalfinanceapp.domain.repository.UserProfileRepository bindUserProfileRepository(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.repository.UserProfileRepositoryImpl implementation);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.grove.personalfinanceapp.domain.repository.CategoryRepository bindCategoryRepository(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.repository.CategoryRepositoryImpl implementation);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.grove.personalfinanceapp.domain.repository.TransactionRepository bindTransactionRepository(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.repository.TransactionRepositoryImpl implementation);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.grove.personalfinanceapp.domain.repository.BudgetRepository bindBudgetRepository(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.repository.BudgetRepositoryImpl implementation);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.grove.personalfinanceapp.domain.repository.SavingsGoalRepository bindSavingsGoalRepository(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.repository.SavingsGoalRepositoryImpl implementation);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.grove.personalfinanceapp.domain.repository.DashboardRepository bindDashboardRepository(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.repository.DashboardRepositoryImpl implementation);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.grove.personalfinanceapp.domain.repository.UserPreferencesRepository bindUserPreferencesRepository(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.repository.UserPreferencesRepositoryImpl implementation);
}