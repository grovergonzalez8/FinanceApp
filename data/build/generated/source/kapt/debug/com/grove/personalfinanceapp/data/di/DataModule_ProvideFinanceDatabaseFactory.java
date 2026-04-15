package com.grove.personalfinanceapp.data.di;

import android.content.Context;
import com.grove.personalfinanceapp.data.local.database.FinanceDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DataModule_ProvideFinanceDatabaseFactory implements Factory<FinanceDatabase> {
  private final Provider<Context> contextProvider;

  public DataModule_ProvideFinanceDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public FinanceDatabase get() {
    return provideFinanceDatabase(contextProvider.get());
  }

  public static DataModule_ProvideFinanceDatabaseFactory create(Provider<Context> contextProvider) {
    return new DataModule_ProvideFinanceDatabaseFactory(contextProvider);
  }

  public static FinanceDatabase provideFinanceDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideFinanceDatabase(context));
  }
}
