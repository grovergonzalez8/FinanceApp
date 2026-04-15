package com.grove.personalfinanceapp.data.repository;

import com.grove.personalfinanceapp.data.preferences.UserPreferencesDataStoreSource;
import com.grove.personalfinanceapp.domain.common.DomainResult;
import com.grove.personalfinanceapp.domain.model.UserPreferences;
import com.grove.personalfinanceapp.domain.repository.UserPreferencesRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/grove/personalfinanceapp/data/repository/UserPreferencesRepositoryImpl;", "Lcom/grove/personalfinanceapp/domain/repository/UserPreferencesRepository;", "userPreferencesDataStoreSource", "Lcom/grove/personalfinanceapp/data/preferences/UserPreferencesDataStoreSource;", "(Lcom/grove/personalfinanceapp/data/preferences/UserPreferencesDataStoreSource;)V", "observeUserPreferences", "Lkotlinx/coroutines/flow/Flow;", "Lcom/grove/personalfinanceapp/domain/model/UserPreferences;", "userId", "", "saveUserPreferences", "Lcom/grove/personalfinanceapp/domain/common/DomainResult;", "preferences", "(Lcom/grove/personalfinanceapp/domain/model/UserPreferences;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class UserPreferencesRepositoryImpl implements com.grove.personalfinanceapp.domain.repository.UserPreferencesRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.grove.personalfinanceapp.data.preferences.UserPreferencesDataStoreSource userPreferencesDataStoreSource = null;
    
    @javax.inject.Inject()
    public UserPreferencesRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.preferences.UserPreferencesDataStoreSource userPreferencesDataStoreSource) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.grove.personalfinanceapp.domain.model.UserPreferences> observeUserPreferences(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveUserPreferences(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.domain.model.UserPreferences preferences, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.grove.personalfinanceapp.domain.common.DomainResult<com.grove.personalfinanceapp.domain.model.UserPreferences>> $completion) {
        return null;
    }
}