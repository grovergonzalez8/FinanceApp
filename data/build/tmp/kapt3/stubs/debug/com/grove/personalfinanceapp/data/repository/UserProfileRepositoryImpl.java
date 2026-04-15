package com.grove.personalfinanceapp.data.repository;

import com.grove.personalfinanceapp.data.local.dao.UserProfileDao;
import com.grove.personalfinanceapp.domain.common.DomainError;
import com.grove.personalfinanceapp.domain.common.DomainResult;
import com.grove.personalfinanceapp.domain.model.UserProfile;
import com.grove.personalfinanceapp.domain.repository.UserProfileRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\tJ\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/grove/personalfinanceapp/data/repository/UserProfileRepositoryImpl;", "Lcom/grove/personalfinanceapp/domain/repository/UserProfileRepository;", "userProfileDao", "Lcom/grove/personalfinanceapp/data/local/dao/UserProfileDao;", "(Lcom/grove/personalfinanceapp/data/local/dao/UserProfileDao;)V", "createUserProfile", "Lcom/grove/personalfinanceapp/domain/common/DomainResult;", "Lcom/grove/personalfinanceapp/domain/model/UserProfile;", "profile", "(Lcom/grove/personalfinanceapp/domain/model/UserProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeUserProfile", "Lkotlinx/coroutines/flow/Flow;", "userId", "", "updateUserProfile", "data_debug"})
public final class UserProfileRepositoryImpl implements com.grove.personalfinanceapp.domain.repository.UserProfileRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.grove.personalfinanceapp.data.local.dao.UserProfileDao userProfileDao = null;
    
    @javax.inject.Inject()
    public UserProfileRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.dao.UserProfileDao userProfileDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.grove.personalfinanceapp.domain.model.UserProfile> observeUserProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object createUserProfile(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.domain.model.UserProfile profile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.grove.personalfinanceapp.domain.common.DomainResult<com.grove.personalfinanceapp.domain.model.UserProfile>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateUserProfile(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.domain.model.UserProfile profile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.grove.personalfinanceapp.domain.common.DomainResult<com.grove.personalfinanceapp.domain.model.UserProfile>> $completion) {
        return null;
    }
}