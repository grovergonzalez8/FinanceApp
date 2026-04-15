package com.grove.personalfinanceapp.data.repository;

import com.grove.personalfinanceapp.data.local.dao.BudgetDao;
import com.grove.personalfinanceapp.domain.common.DomainError;
import com.grove.personalfinanceapp.domain.common.DomainResult;
import com.grove.personalfinanceapp.domain.model.Budget;
import com.grove.personalfinanceapp.domain.repository.BudgetRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\tJ\u001c\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/grove/personalfinanceapp/data/repository/BudgetRepositoryImpl;", "Lcom/grove/personalfinanceapp/domain/repository/BudgetRepository;", "budgetDao", "Lcom/grove/personalfinanceapp/data/local/dao/BudgetDao;", "(Lcom/grove/personalfinanceapp/data/local/dao/BudgetDao;)V", "createBudget", "Lcom/grove/personalfinanceapp/domain/common/DomainResult;", "Lcom/grove/personalfinanceapp/domain/model/Budget;", "budget", "(Lcom/grove/personalfinanceapp/domain/model/Budget;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeBudgets", "Lkotlinx/coroutines/flow/Flow;", "", "userId", "", "updateBudget", "data_debug"})
public final class BudgetRepositoryImpl implements com.grove.personalfinanceapp.domain.repository.BudgetRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.grove.personalfinanceapp.data.local.dao.BudgetDao budgetDao = null;
    
    @javax.inject.Inject()
    public BudgetRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.dao.BudgetDao budgetDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.grove.personalfinanceapp.domain.model.Budget>> observeBudgets(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object createBudget(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.domain.model.Budget budget, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.grove.personalfinanceapp.domain.common.DomainResult<com.grove.personalfinanceapp.domain.model.Budget>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateBudget(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.domain.model.Budget budget, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.grove.personalfinanceapp.domain.common.DomainResult<com.grove.personalfinanceapp.domain.model.Budget>> $completion) {
        return null;
    }
}