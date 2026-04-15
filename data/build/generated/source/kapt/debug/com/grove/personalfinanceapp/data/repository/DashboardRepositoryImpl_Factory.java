package com.grove.personalfinanceapp.data.repository;

import com.grove.personalfinanceapp.data.local.dao.BudgetDao;
import com.grove.personalfinanceapp.data.local.dao.CategoryDao;
import com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao;
import com.grove.personalfinanceapp.data.local.dao.TransactionDao;
import com.grove.personalfinanceapp.data.local.dao.UserProfileDao;
import com.grove.personalfinanceapp.data.preferences.UserPreferencesDataStoreSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.time.Clock;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class DashboardRepositoryImpl_Factory implements Factory<DashboardRepositoryImpl> {
  private final Provider<TransactionDao> transactionDaoProvider;

  private final Provider<CategoryDao> categoryDaoProvider;

  private final Provider<BudgetDao> budgetDaoProvider;

  private final Provider<SavingsGoalDao> savingsGoalDaoProvider;

  private final Provider<UserProfileDao> userProfileDaoProvider;

  private final Provider<UserPreferencesDataStoreSource> userPreferencesDataStoreSourceProvider;

  private final Provider<Clock> clockProvider;

  public DashboardRepositoryImpl_Factory(Provider<TransactionDao> transactionDaoProvider,
      Provider<CategoryDao> categoryDaoProvider, Provider<BudgetDao> budgetDaoProvider,
      Provider<SavingsGoalDao> savingsGoalDaoProvider,
      Provider<UserProfileDao> userProfileDaoProvider,
      Provider<UserPreferencesDataStoreSource> userPreferencesDataStoreSourceProvider,
      Provider<Clock> clockProvider) {
    this.transactionDaoProvider = transactionDaoProvider;
    this.categoryDaoProvider = categoryDaoProvider;
    this.budgetDaoProvider = budgetDaoProvider;
    this.savingsGoalDaoProvider = savingsGoalDaoProvider;
    this.userProfileDaoProvider = userProfileDaoProvider;
    this.userPreferencesDataStoreSourceProvider = userPreferencesDataStoreSourceProvider;
    this.clockProvider = clockProvider;
  }

  @Override
  public DashboardRepositoryImpl get() {
    return newInstance(transactionDaoProvider.get(), categoryDaoProvider.get(), budgetDaoProvider.get(), savingsGoalDaoProvider.get(), userProfileDaoProvider.get(), userPreferencesDataStoreSourceProvider.get(), clockProvider.get());
  }

  public static DashboardRepositoryImpl_Factory create(
      Provider<TransactionDao> transactionDaoProvider, Provider<CategoryDao> categoryDaoProvider,
      Provider<BudgetDao> budgetDaoProvider, Provider<SavingsGoalDao> savingsGoalDaoProvider,
      Provider<UserProfileDao> userProfileDaoProvider,
      Provider<UserPreferencesDataStoreSource> userPreferencesDataStoreSourceProvider,
      Provider<Clock> clockProvider) {
    return new DashboardRepositoryImpl_Factory(transactionDaoProvider, categoryDaoProvider, budgetDaoProvider, savingsGoalDaoProvider, userProfileDaoProvider, userPreferencesDataStoreSourceProvider, clockProvider);
  }

  public static DashboardRepositoryImpl newInstance(TransactionDao transactionDao,
      CategoryDao categoryDao, BudgetDao budgetDao, SavingsGoalDao savingsGoalDao,
      UserProfileDao userProfileDao, UserPreferencesDataStoreSource userPreferencesDataStoreSource,
      Clock clock) {
    return new DashboardRepositoryImpl(transactionDao, categoryDao, budgetDao, savingsGoalDao, userProfileDao, userPreferencesDataStoreSource, clock);
  }
}
