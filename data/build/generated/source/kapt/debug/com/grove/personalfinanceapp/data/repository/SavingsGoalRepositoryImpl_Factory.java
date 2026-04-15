package com.grove.personalfinanceapp.data.repository;

import com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao;
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
public final class SavingsGoalRepositoryImpl_Factory implements Factory<SavingsGoalRepositoryImpl> {
  private final Provider<SavingsGoalDao> savingsGoalDaoProvider;

  private final Provider<Clock> clockProvider;

  public SavingsGoalRepositoryImpl_Factory(Provider<SavingsGoalDao> savingsGoalDaoProvider,
      Provider<Clock> clockProvider) {
    this.savingsGoalDaoProvider = savingsGoalDaoProvider;
    this.clockProvider = clockProvider;
  }

  @Override
  public SavingsGoalRepositoryImpl get() {
    return newInstance(savingsGoalDaoProvider.get(), clockProvider.get());
  }

  public static SavingsGoalRepositoryImpl_Factory create(
      Provider<SavingsGoalDao> savingsGoalDaoProvider, Provider<Clock> clockProvider) {
    return new SavingsGoalRepositoryImpl_Factory(savingsGoalDaoProvider, clockProvider);
  }

  public static SavingsGoalRepositoryImpl newInstance(SavingsGoalDao savingsGoalDao, Clock clock) {
    return new SavingsGoalRepositoryImpl(savingsGoalDao, clock);
  }
}
