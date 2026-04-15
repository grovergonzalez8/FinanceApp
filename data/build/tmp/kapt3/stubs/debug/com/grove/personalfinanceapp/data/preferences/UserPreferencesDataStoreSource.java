package com.grove.personalfinanceapp.data.preferences;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import com.grove.personalfinanceapp.domain.model.DashboardPeriod;
import com.grove.personalfinanceapp.domain.model.UserPreferences;
import kotlinx.coroutines.flow.Flow;
import java.io.IOException;
import java.time.DayOfWeek;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\bH\u0002J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\bH\u0002J\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000f2\u0006\u0010\t\u001a\u00020\bJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00072\u0006\u0010\t\u001a\u00020\bH\u0002J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u00072\u0006\u0010\t\u001a\u00020\bH\u0002J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00072\u0006\u0010\t\u001a\u00020\bH\u0002J\u0016\u0010\u0019\u001a\u0004\u0018\u00010\f*\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/grove/personalfinanceapp/data/preferences/UserPreferencesDataStoreSource;", "", "dataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "(Landroidx/datastore/core/DataStore;)V", "currencyCodeKey", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "userId", "dashboardPeriodKey", "getUserPreferences", "Lcom/grove/personalfinanceapp/domain/model/UserPreferences;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeUserPreferences", "Lkotlinx/coroutines/flow/Flow;", "prioritizeSavingsGoalsKey", "", "saveUserPreferences", "", "preferences", "(Lcom/grove/personalfinanceapp/domain/model/UserPreferences;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "showBalancesKey", "weekStartsOnKey", "", "toUserPreferences", "data_debug"})
public final class UserPreferencesDataStoreSource {
    @org.jetbrains.annotations.NotNull()
    private final androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> dataStore = null;
    
    @javax.inject.Inject()
    public UserPreferencesDataStoreSource(@org.jetbrains.annotations.NotNull()
    androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> dataStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.grove.personalfinanceapp.domain.model.UserPreferences> observeUserPreferences(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUserPreferences(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.grove.personalfinanceapp.domain.model.UserPreferences> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveUserPreferences(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.domain.model.UserPreferences preferences, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final com.grove.personalfinanceapp.domain.model.UserPreferences toUserPreferences(androidx.datastore.preferences.core.Preferences $this$toUserPreferences, java.lang.String userId) {
        return null;
    }
    
    private final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> currencyCodeKey(java.lang.String userId) {
        return null;
    }
    
    private final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> dashboardPeriodKey(java.lang.String userId) {
        return null;
    }
    
    private final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> showBalancesKey(java.lang.String userId) {
        return null;
    }
    
    private final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> prioritizeSavingsGoalsKey(java.lang.String userId) {
        return null;
    }
    
    private final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> weekStartsOnKey(java.lang.String userId) {
        return null;
    }
}