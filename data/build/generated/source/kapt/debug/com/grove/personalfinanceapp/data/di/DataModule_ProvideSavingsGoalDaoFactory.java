package com.grove.personalfinanceapp.data.di;

import com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao;
import com.grove.personalfinanceapp.data.local.database.FinanceDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class DataModule_ProvideSavingsGoalDaoFactory implements Factory<SavingsGoalDao> {
  private final Provider<FinanceDatabase> databaseProvider;

  public DataModule_ProvideSavingsGoalDaoFactory(Provider<FinanceDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public SavingsGoalDao get() {
    return provideSavingsGoalDao(databaseProvider.get());
  }

  public static DataModule_ProvideSavingsGoalDaoFactory create(
      Provider<FinanceDatabase> databaseProvider) {
    return new DataModule_ProvideSavingsGoalDaoFactory(databaseProvider);
  }

  public static SavingsGoalDao provideSavingsGoalDao(FinanceDatabase database) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideSavingsGoalDao(database));
  }
}
