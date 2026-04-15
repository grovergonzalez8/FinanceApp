package com.grove.personalfinanceapp.data.repository;

import com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao;
import com.grove.personalfinanceapp.domain.common.DomainError;
import com.grove.personalfinanceapp.domain.common.DomainResult;
import com.grove.personalfinanceapp.domain.model.SavingsGoal;
import com.grove.personalfinanceapp.domain.repository.SavingsGoalRepository;
import kotlinx.coroutines.flow.Flow;
import java.math.BigDecimal;
import java.time.Clock;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J$\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0010\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u000bH\u0016J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0010\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/grove/personalfinanceapp/data/repository/SavingsGoalRepositoryImpl;", "Lcom/grove/personalfinanceapp/domain/repository/SavingsGoalRepository;", "savingsGoalDao", "Lcom/grove/personalfinanceapp/data/local/dao/SavingsGoalDao;", "clock", "Ljava/time/Clock;", "(Lcom/grove/personalfinanceapp/data/local/dao/SavingsGoalDao;Ljava/time/Clock;)V", "addContribution", "Lcom/grove/personalfinanceapp/domain/common/DomainResult;", "Lcom/grove/personalfinanceapp/domain/model/SavingsGoal;", "goalId", "", "amount", "Ljava/math/BigDecimal;", "(Ljava/lang/String;Ljava/math/BigDecimal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createSavingsGoal", "goal", "(Lcom/grove/personalfinanceapp/domain/model/SavingsGoal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeSavingsGoals", "Lkotlinx/coroutines/flow/Flow;", "", "userId", "updateSavingsGoal", "data_debug"})
public final class SavingsGoalRepositoryImpl implements com.grove.personalfinanceapp.domain.repository.SavingsGoalRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao savingsGoalDao = null;
    @org.jetbrains.annotations.NotNull()
    private final java.time.Clock clock = null;
    
    @javax.inject.Inject()
    public SavingsGoalRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao savingsGoalDao, @org.jetbrains.annotations.NotNull()
    java.time.Clock clock) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.grove.personalfinanceapp.domain.model.SavingsGoal>> observeSavingsGoals(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object createSavingsGoal(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.domain.model.SavingsGoal goal, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.grove.personalfinanceapp.domain.common.DomainResult<com.grove.personalfinanceapp.domain.model.SavingsGoal>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateSavingsGoal(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.domain.model.SavingsGoal goal, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.grove.personalfinanceapp.domain.common.DomainResult<com.grove.personalfinanceapp.domain.model.SavingsGoal>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addContribution(@org.jetbrains.annotations.NotNull()
    java.lang.String goalId, @org.jetbrains.annotations.NotNull()
    java.math.BigDecimal amount, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.grove.personalfinanceapp.domain.common.DomainResult<com.grove.personalfinanceapp.domain.model.SavingsGoal>> $completion) {
        return null;
    }
}