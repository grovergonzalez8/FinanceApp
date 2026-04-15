package com.grove.personalfinanceapp.data.di;

import com.grove.personalfinanceapp.data.local.dao.UserProfileDao;
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
public final class DataModule_ProvideUserProfileDaoFactory implements Factory<UserProfileDao> {
  private final Provider<FinanceDatabase> databaseProvider;

  public DataModule_ProvideUserProfileDaoFactory(Provider<FinanceDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public UserProfileDao get() {
    return provideUserProfileDao(databaseProvider.get());
  }

  public static DataModule_ProvideUserProfileDaoFactory create(
      Provider<FinanceDatabase> databaseProvider) {
    return new DataModule_ProvideUserProfileDaoFactory(databaseProvider);
  }

  public static UserProfileDao provideUserProfileDao(FinanceDatabase database) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideUserProfileDao(database));
  }
}
