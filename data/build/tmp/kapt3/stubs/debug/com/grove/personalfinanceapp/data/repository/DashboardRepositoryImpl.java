package com.grove.personalfinanceapp.data.repository;

import com.grove.personalfinanceapp.data.local.dao.BudgetDao;
import com.grove.personalfinanceapp.data.local.dao.CategoryDao;
import com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao;
import com.grove.personalfinanceapp.data.local.dao.TransactionDao;
import com.grove.personalfinanceapp.data.local.dao.UserProfileDao;
import com.grove.personalfinanceapp.data.preferences.UserPreferencesDataStoreSource;
import com.grove.personalfinanceapp.domain.common.DomainResult;
import com.grove.personalfinanceapp.domain.model.Budget;
import com.grove.personalfinanceapp.domain.model.BudgetProgress;
import com.grove.personalfinanceapp.domain.model.Category;
import com.grove.personalfinanceapp.domain.model.CategorySpending;
import com.grove.personalfinanceapp.domain.model.DashboardPeriod;
import com.grove.personalfinanceapp.domain.model.DashboardSummary;
import com.grove.personalfinanceapp.domain.model.DateRange;
import com.grove.personalfinanceapp.domain.model.FinancialInsight;
import com.grove.personalfinanceapp.domain.model.FinancialInsightType;
import com.grove.personalfinanceapp.domain.model.FinancialTransaction;
import com.grove.personalfinanceapp.domain.model.InsightSeverity;
import com.grove.personalfinanceapp.domain.model.SavingsGoal;
import com.grove.personalfinanceapp.domain.model.SavingsGoalPriority;
import com.grove.personalfinanceapp.domain.model.TransactionType;
import com.grove.personalfinanceapp.domain.model.UserPreferences;
import com.grove.personalfinanceapp.domain.model.UserProfile;
import com.grove.personalfinanceapp.domain.repository.DashboardRepository;
import kotlinx.coroutines.flow.Flow;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00fc\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 d2\u00020\u0001:\u0002deB?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J.\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J.\u0010\u001c\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J.\u0010\u001d\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J.\u0010\u001e\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J6\u0010\u001f\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002JF\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010%\u001a\u00020&2\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020(0#2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0#2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020-0,H\u0002J.\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00150#2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J$\u0010/\u001a\b\u0012\u0004\u0012\u0002000#2\f\u00101\u001a\b\u0012\u0004\u0012\u0002000#2\u0006\u00102\u001a\u000203H\u0002Jd\u00104\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u00105\u001a\u0002062\f\u00107\u001a\b\u0012\u0004\u0012\u00020*0#2\f\u00108\u001a\b\u0012\u0004\u0012\u00020-0#2\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020(0#2\f\u00101\u001a\b\u0012\u0004\u0012\u0002000#2\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010;\u001a\u0004\u0018\u00010<H\u0002Jl\u0010=\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u00105\u001a\u0002062\u0006\u0010>\u001a\u00020&2\f\u00107\u001a\b\u0012\u0004\u0012\u00020*0#2\f\u00108\u001a\b\u0012\u0004\u0012\u00020-0#2\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020(0#2\f\u00101\u001a\b\u0012\u0004\u0012\u0002000#2\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010;\u001a\u0004\u0018\u00010<H\u0002J0\u0010?\u001a\b\u0012\u0004\u0012\u00020@0#2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0#2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020-0,H\u0002J\u0018\u0010A\u001a\u00020&2\u0006\u00105\u001a\u0002062\u0006\u0010B\u001a\u00020CH\u0002J\u0018\u0010D\u001a\u00020&2\u0006\u00105\u001a\u0002062\u0006\u0010E\u001a\u00020&H\u0002J\\\u0010F\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\u00172\u0006\u0010L\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010N\u001a\u0004\u0018\u00010O2\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\u0017H\u0002J*\u0010Q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150#0R2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u00105\u001a\u000206H\u0096@\u00a2\u0006\u0002\u0010SJ\u001e\u0010T\u001a\b\u0012\u0004\u0012\u00020\u00190U2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u00105\u001a\u000206H\u0016JF\u0010V\u001a\u00020\u00172\f\u00107\u001a\b\u0012\u0004\u0012\u00020*0#2\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020(0#2\f\u00101\u001a\b\u0012\u0004\u0012\u0002000#2\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010;\u001a\u0004\u0018\u00010<H\u0002J\u0010\u0010W\u001a\u00020\u00172\u0006\u0010X\u001a\u00020OH\u0002J\u0014\u0010Y\u001a\u000203*\u00020&2\u0006\u0010Z\u001a\u00020&H\u0002J\f\u0010[\u001a\u00020\\*\u00020JH\u0002J\f\u0010[\u001a\u00020\\*\u00020]H\u0002J\u0012\u0010^\u001a\u00020O*\b\u0012\u0004\u0012\u00020*0_H\u0002J\u0014\u0010`\u001a\u00020a*\u00020\u001b2\u0006\u0010b\u001a\u00020cH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006f"}, d2 = {"Lcom/grove/personalfinanceapp/data/repository/DashboardRepositoryImpl;", "Lcom/grove/personalfinanceapp/domain/repository/DashboardRepository;", "transactionDao", "Lcom/grove/personalfinanceapp/data/local/dao/TransactionDao;", "categoryDao", "Lcom/grove/personalfinanceapp/data/local/dao/CategoryDao;", "budgetDao", "Lcom/grove/personalfinanceapp/data/local/dao/BudgetDao;", "savingsGoalDao", "Lcom/grove/personalfinanceapp/data/local/dao/SavingsGoalDao;", "userProfileDao", "Lcom/grove/personalfinanceapp/data/local/dao/UserProfileDao;", "userPreferencesDataStoreSource", "Lcom/grove/personalfinanceapp/data/preferences/UserPreferencesDataStoreSource;", "clock", "Ljava/time/Clock;", "(Lcom/grove/personalfinanceapp/data/local/dao/TransactionDao;Lcom/grove/personalfinanceapp/data/local/dao/CategoryDao;Lcom/grove/personalfinanceapp/data/local/dao/BudgetDao;Lcom/grove/personalfinanceapp/data/local/dao/SavingsGoalDao;Lcom/grove/personalfinanceapp/data/local/dao/UserProfileDao;Lcom/grove/personalfinanceapp/data/preferences/UserPreferencesDataStoreSource;Ljava/time/Clock;)V", "addBudgetRiskInsights", "", "insights", "", "Lcom/grove/personalfinanceapp/domain/model/FinancialInsight;", "userId", "", "summary", "Lcom/grove/personalfinanceapp/domain/model/DashboardSummary;", "generatedAt", "Ljava/time/Instant;", "addCashFlowInsight", "addCategoryConcentrationInsight", "addSavingsProgressInsight", "addSpendingSpikeInsight", "currentSummary", "previousSummary", "buildBudgetProgress", "", "Lcom/grove/personalfinanceapp/domain/model/BudgetProgress;", "summaryRange", "Lcom/grove/personalfinanceapp/domain/model/DateRange;", "budgets", "Lcom/grove/personalfinanceapp/domain/model/Budget;", "expenseTransactions", "Lcom/grove/personalfinanceapp/domain/model/FinancialTransaction;", "categoryById", "", "Lcom/grove/personalfinanceapp/domain/model/Category;", "buildInsights", "buildPrioritySavingsGoals", "Lcom/grove/personalfinanceapp/domain/model/SavingsGoal;", "savingsGoals", "prioritize", "", "buildSummaryForCurrentPeriod", "period", "Lcom/grove/personalfinanceapp/domain/model/DashboardPeriod;", "transactions", "categories", "profile", "Lcom/grove/personalfinanceapp/domain/model/UserProfile;", "preferences", "Lcom/grove/personalfinanceapp/domain/model/UserPreferences;", "buildSummaryForRange", "range", "buildTopSpendingCategories", "Lcom/grove/personalfinanceapp/domain/model/CategorySpending;", "calculateCurrentRange", "weekStartsOn", "Ljava/time/DayOfWeek;", "calculatePreviousRange", "currentRange", "createInsight", "type", "Lcom/grove/personalfinanceapp/domain/model/FinancialInsightType;", "severity", "Lcom/grove/personalfinanceapp/domain/model/InsightSeverity;", "title", "message", "relatedCategoryId", "highlightAmount", "Ljava/math/BigDecimal;", "currencyCode", "generateFinancialInsights", "Lcom/grove/personalfinanceapp/domain/common/DomainResult;", "(Ljava/lang/String;Lcom/grove/personalfinanceapp/domain/model/DashboardPeriod;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeDashboardSummary", "Lkotlinx/coroutines/flow/Flow;", "resolveCurrencyCode", "toPercentString", "value", "overlaps", "other", "rank", "", "Lcom/grove/personalfinanceapp/domain/model/SavingsGoalPriority;", "sumAmounts", "", "toLocalDate", "Ljava/time/LocalDate;", "zoneId", "Ljava/time/ZoneId;", "Companion", "DashboardData", "data_debug"})
public final class DashboardRepositoryImpl implements com.grove.personalfinanceapp.domain.repository.DashboardRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.grove.personalfinanceapp.data.local.dao.TransactionDao transactionDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.grove.personalfinanceapp.data.local.dao.CategoryDao categoryDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.grove.personalfinanceapp.data.local.dao.BudgetDao budgetDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao savingsGoalDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.grove.personalfinanceapp.data.local.dao.UserProfileDao userProfileDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.grove.personalfinanceapp.data.preferences.UserPreferencesDataStoreSource userPreferencesDataStoreSource = null;
    @org.jetbrains.annotations.NotNull()
    private final java.time.Clock clock = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DEFAULT_CURRENCY_CODE = "USD";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String UNKNOWN_CATEGORY_NAME = "Unknown category";
    private static final int MAX_TOP_SPENDING_CATEGORIES = 5;
    private static final int MAX_PRIORITY_GOALS = 3;
    private static final int MAX_INSIGHTS = 5;
    private static final int DAYS_IN_WEEK = 7;
    @org.jetbrains.annotations.NotNull()
    private static final java.time.DayOfWeek DEFAULT_WEEK_START = java.time.DayOfWeek.MONDAY;
    @org.jetbrains.annotations.NotNull()
    private static final java.math.BigDecimal PERCENT_MULTIPLIER = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.math.BigDecimal SPENDING_SPIKE_THRESHOLD = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.math.BigDecimal CRITICAL_SPENDING_SPIKE_THRESHOLD = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.math.BigDecimal BUDGET_WARNING_THRESHOLD = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.math.BigDecimal CATEGORY_CONCENTRATION_THRESHOLD = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.math.BigDecimal SAVINGS_PROGRESS_THRESHOLD = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.grove.personalfinanceapp.data.repository.DashboardRepositoryImpl.Companion Companion = null;
    
    @javax.inject.Inject()
    public DashboardRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.dao.TransactionDao transactionDao, @org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.dao.CategoryDao categoryDao, @org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.dao.BudgetDao budgetDao, @org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao savingsGoalDao, @org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.dao.UserProfileDao userProfileDao, @org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.preferences.UserPreferencesDataStoreSource userPreferencesDataStoreSource, @org.jetbrains.annotations.NotNull()
    java.time.Clock clock) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.grove.personalfinanceapp.domain.model.DashboardSummary> observeDashboardSummary(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.domain.model.DashboardPeriod period) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object generateFinancialInsights(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.domain.model.DashboardPeriod period, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.grove.personalfinanceapp.domain.common.DomainResult<? extends java.util.List<com.grove.personalfinanceapp.domain.model.FinancialInsight>>> $completion) {
        return null;
    }
    
    private final com.grove.personalfinanceapp.domain.model.DashboardSummary buildSummaryForCurrentPeriod(java.lang.String userId, com.grove.personalfinanceapp.domain.model.DashboardPeriod period, java.util.List<com.grove.personalfinanceapp.domain.model.FinancialTransaction> transactions, java.util.List<com.grove.personalfinanceapp.domain.model.Category> categories, java.util.List<com.grove.personalfinanceapp.domain.model.Budget> budgets, java.util.List<com.grove.personalfinanceapp.domain.model.SavingsGoal> savingsGoals, com.grove.personalfinanceapp.domain.model.UserProfile profile, com.grove.personalfinanceapp.domain.model.UserPreferences preferences) {
        return null;
    }
    
    private final com.grove.personalfinanceapp.domain.model.DashboardSummary buildSummaryForRange(java.lang.String userId, com.grove.personalfinanceapp.domain.model.DashboardPeriod period, com.grove.personalfinanceapp.domain.model.DateRange range, java.util.List<com.grove.personalfinanceapp.domain.model.FinancialTransaction> transactions, java.util.List<com.grove.personalfinanceapp.domain.model.Category> categories, java.util.List<com.grove.personalfinanceapp.domain.model.Budget> budgets, java.util.List<com.grove.personalfinanceapp.domain.model.SavingsGoal> savingsGoals, com.grove.personalfinanceapp.domain.model.UserProfile profile, com.grove.personalfinanceapp.domain.model.UserPreferences preferences) {
        return null;
    }
    
    private final java.util.List<com.grove.personalfinanceapp.domain.model.BudgetProgress> buildBudgetProgress(com.grove.personalfinanceapp.domain.model.DateRange summaryRange, java.util.List<com.grove.personalfinanceapp.domain.model.Budget> budgets, java.util.List<com.grove.personalfinanceapp.domain.model.FinancialTransaction> expenseTransactions, java.util.Map<java.lang.String, com.grove.personalfinanceapp.domain.model.Category> categoryById) {
        return null;
    }
    
    private final java.util.List<com.grove.personalfinanceapp.domain.model.CategorySpending> buildTopSpendingCategories(java.util.List<com.grove.personalfinanceapp.domain.model.FinancialTransaction> expenseTransactions, java.util.Map<java.lang.String, com.grove.personalfinanceapp.domain.model.Category> categoryById) {
        return null;
    }
    
    private final java.util.List<com.grove.personalfinanceapp.domain.model.SavingsGoal> buildPrioritySavingsGoals(java.util.List<com.grove.personalfinanceapp.domain.model.SavingsGoal> savingsGoals, boolean prioritize) {
        return null;
    }
    
    private final java.lang.String resolveCurrencyCode(java.util.List<com.grove.personalfinanceapp.domain.model.FinancialTransaction> transactions, java.util.List<com.grove.personalfinanceapp.domain.model.Budget> budgets, java.util.List<com.grove.personalfinanceapp.domain.model.SavingsGoal> savingsGoals, com.grove.personalfinanceapp.domain.model.UserProfile profile, com.grove.personalfinanceapp.domain.model.UserPreferences preferences) {
        return null;
    }
    
    private final com.grove.personalfinanceapp.domain.model.DateRange calculateCurrentRange(com.grove.personalfinanceapp.domain.model.DashboardPeriod period, java.time.DayOfWeek weekStartsOn) {
        return null;
    }
    
    private final com.grove.personalfinanceapp.domain.model.DateRange calculatePreviousRange(com.grove.personalfinanceapp.domain.model.DashboardPeriod period, com.grove.personalfinanceapp.domain.model.DateRange currentRange) {
        return null;
    }
    
    private final java.util.List<com.grove.personalfinanceapp.domain.model.FinancialInsight> buildInsights(java.lang.String userId, com.grove.personalfinanceapp.domain.model.DashboardSummary currentSummary, com.grove.personalfinanceapp.domain.model.DashboardSummary previousSummary, java.time.Instant generatedAt) {
        return null;
    }
    
    private final void addSpendingSpikeInsight(java.util.List<com.grove.personalfinanceapp.domain.model.FinancialInsight> insights, java.lang.String userId, com.grove.personalfinanceapp.domain.model.DashboardSummary currentSummary, com.grove.personalfinanceapp.domain.model.DashboardSummary previousSummary, java.time.Instant generatedAt) {
    }
    
    private final void addBudgetRiskInsights(java.util.List<com.grove.personalfinanceapp.domain.model.FinancialInsight> insights, java.lang.String userId, com.grove.personalfinanceapp.domain.model.DashboardSummary summary, java.time.Instant generatedAt) {
    }
    
    private final void addCashFlowInsight(java.util.List<com.grove.personalfinanceapp.domain.model.FinancialInsight> insights, java.lang.String userId, com.grove.personalfinanceapp.domain.model.DashboardSummary summary, java.time.Instant generatedAt) {
    }
    
    private final void addCategoryConcentrationInsight(java.util.List<com.grove.personalfinanceapp.domain.model.FinancialInsight> insights, java.lang.String userId, com.grove.personalfinanceapp.domain.model.DashboardSummary summary, java.time.Instant generatedAt) {
    }
    
    private final void addSavingsProgressInsight(java.util.List<com.grove.personalfinanceapp.domain.model.FinancialInsight> insights, java.lang.String userId, com.grove.personalfinanceapp.domain.model.DashboardSummary summary, java.time.Instant generatedAt) {
    }
    
    private final com.grove.personalfinanceapp.domain.model.FinancialInsight createInsight(java.lang.String userId, com.grove.personalfinanceapp.domain.model.FinancialInsightType type, com.grove.personalfinanceapp.domain.model.InsightSeverity severity, java.lang.String title, java.lang.String message, java.time.Instant generatedAt, java.lang.String relatedCategoryId, java.math.BigDecimal highlightAmount, java.lang.String currencyCode) {
        return null;
    }
    
    private final java.math.BigDecimal sumAmounts(java.lang.Iterable<com.grove.personalfinanceapp.domain.model.FinancialTransaction> $this$sumAmounts) {
        return null;
    }
    
    private final java.time.LocalDate toLocalDate(java.time.Instant $this$toLocalDate, java.time.ZoneId zoneId) {
        return null;
    }
    
    private final boolean overlaps(com.grove.personalfinanceapp.domain.model.DateRange $this$overlaps, com.grove.personalfinanceapp.domain.model.DateRange other) {
        return false;
    }
    
    private final int rank(com.grove.personalfinanceapp.domain.model.SavingsGoalPriority $this$rank) {
        return 0;
    }
    
    private final int rank(com.grove.personalfinanceapp.domain.model.InsightSeverity $this$rank) {
        return 0;
    }
    
    private final java.lang.String toPercentString(java.math.BigDecimal value) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/grove/personalfinanceapp/data/repository/DashboardRepositoryImpl$Companion;", "", "()V", "BUDGET_WARNING_THRESHOLD", "Ljava/math/BigDecimal;", "CATEGORY_CONCENTRATION_THRESHOLD", "CRITICAL_SPENDING_SPIKE_THRESHOLD", "DAYS_IN_WEEK", "", "DEFAULT_CURRENCY_CODE", "", "DEFAULT_WEEK_START", "Ljava/time/DayOfWeek;", "MAX_INSIGHTS", "MAX_PRIORITY_GOALS", "MAX_TOP_SPENDING_CATEGORIES", "PERCENT_MULTIPLIER", "SAVINGS_PROGRESS_THRESHOLD", "SPENDING_SPIKE_THRESHOLD", "UNKNOWN_CATEGORY_NAME", "data_debug"})
    static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001BG\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\rJ\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H\u00c6\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u0003H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\fH\u00c6\u0003JU\u0010\u001a\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020!H\u00d6\u0001R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f\u00a8\u0006\""}, d2 = {"Lcom/grove/personalfinanceapp/data/repository/DashboardRepositoryImpl$DashboardData;", "", "transactions", "", "Lcom/grove/personalfinanceapp/domain/model/FinancialTransaction;", "categories", "Lcom/grove/personalfinanceapp/domain/model/Category;", "budgets", "Lcom/grove/personalfinanceapp/domain/model/Budget;", "savingsGoals", "Lcom/grove/personalfinanceapp/domain/model/SavingsGoal;", "profile", "Lcom/grove/personalfinanceapp/domain/model/UserProfile;", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/grove/personalfinanceapp/domain/model/UserProfile;)V", "getBudgets", "()Ljava/util/List;", "getCategories", "getProfile", "()Lcom/grove/personalfinanceapp/domain/model/UserProfile;", "getSavingsGoals", "getTransactions", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "data_debug"})
    static final class DashboardData {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.grove.personalfinanceapp.domain.model.FinancialTransaction> transactions = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.grove.personalfinanceapp.domain.model.Category> categories = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.grove.personalfinanceapp.domain.model.Budget> budgets = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.grove.personalfinanceapp.domain.model.SavingsGoal> savingsGoals = null;
        @org.jetbrains.annotations.Nullable()
        private final com.grove.personalfinanceapp.domain.model.UserProfile profile = null;
        
        public DashboardData(@org.jetbrains.annotations.NotNull()
        java.util.List<com.grove.personalfinanceapp.domain.model.FinancialTransaction> transactions, @org.jetbrains.annotations.NotNull()
        java.util.List<com.grove.personalfinanceapp.domain.model.Category> categories, @org.jetbrains.annotations.NotNull()
        java.util.List<com.grove.personalfinanceapp.domain.model.Budget> budgets, @org.jetbrains.annotations.NotNull()
        java.util.List<com.grove.personalfinanceapp.domain.model.SavingsGoal> savingsGoals, @org.jetbrains.annotations.Nullable()
        com.grove.personalfinanceapp.domain.model.UserProfile profile) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.grove.personalfinanceapp.domain.model.FinancialTransaction> getTransactions() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.grove.personalfinanceapp.domain.model.Category> getCategories() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.grove.personalfinanceapp.domain.model.Budget> getBudgets() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.grove.personalfinanceapp.domain.model.SavingsGoal> getSavingsGoals() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.grove.personalfinanceapp.domain.model.UserProfile getProfile() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.grove.personalfinanceapp.domain.model.FinancialTransaction> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.grove.personalfinanceapp.domain.model.Category> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.grove.personalfinanceapp.domain.model.Budget> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.grove.personalfinanceapp.domain.model.SavingsGoal> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.grove.personalfinanceapp.domain.model.UserProfile component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.grove.personalfinanceapp.data.repository.DashboardRepositoryImpl.DashboardData copy(@org.jetbrains.annotations.NotNull()
        java.util.List<com.grove.personalfinanceapp.domain.model.FinancialTransaction> transactions, @org.jetbrains.annotations.NotNull()
        java.util.List<com.grove.personalfinanceapp.domain.model.Category> categories, @org.jetbrains.annotations.NotNull()
        java.util.List<com.grove.personalfinanceapp.domain.model.Budget> budgets, @org.jetbrains.annotations.NotNull()
        java.util.List<com.grove.personalfinanceapp.domain.model.SavingsGoal> savingsGoals, @org.jetbrains.annotations.Nullable()
        com.grove.personalfinanceapp.domain.model.UserProfile profile) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}