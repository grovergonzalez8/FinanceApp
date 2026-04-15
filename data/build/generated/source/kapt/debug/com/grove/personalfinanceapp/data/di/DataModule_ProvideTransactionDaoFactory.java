package com.grove.personalfinanceapp.data.di;

import com.grove.personalfinanceapp.data.local.dao.TransactionDao;
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
public final class DataModule_ProvideTransactionDaoFactory implements Factory<TransactionDao> {
  private final Provider<FinanceDatabase> databaseProvider;

  public DataModule_ProvideTransactionDaoFactory(Provider<FinanceDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public TransactionDao get() {
    return provideTransactionDao(databaseProvider.get());
  }

  public static DataModule_ProvideTransactionDaoFactory create(
      Provider<FinanceDatabase> databaseProvider) {
    return new DataModule_ProvideTransactionDaoFactory(databaseProvider);
  }

  public static TransactionDao provideTransactionDao(FinanceDatabase database) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideTransactionDao(database));
  }
}
