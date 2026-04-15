package com.grove.personalfinanceapp.data.di;

import com.grove.personalfinanceapp.data.local.dao.BudgetDao;
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
public final class DataModule_ProvideBudgetDaoFactory implements Factory<BudgetDao> {
  private final Provider<FinanceDatabase> databaseProvider;

  public DataModule_ProvideBudgetDaoFactory(Provider<FinanceDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public BudgetDao get() {
    return provideBudgetDao(databaseProvider.get());
  }

  public static DataModule_ProvideBudgetDaoFactory create(
      Provider<FinanceDatabase> databaseProvider) {
    return new DataModule_ProvideBudgetDaoFactory(databaseProvider);
  }

  public static BudgetDao provideBudgetDao(FinanceDatabase database) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideBudgetDao(database));
  }
}
