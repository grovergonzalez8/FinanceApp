package com.grove.personalfinanceapp.data.di;

import com.grove.personalfinanceapp.data.local.dao.CategoryDao;
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
public final class DataModule_ProvideCategoryDaoFactory implements Factory<CategoryDao> {
  private final Provider<FinanceDatabase> databaseProvider;

  public DataModule_ProvideCategoryDaoFactory(Provider<FinanceDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public CategoryDao get() {
    return provideCategoryDao(databaseProvider.get());
  }

  public static DataModule_ProvideCategoryDaoFactory create(
      Provider<FinanceDatabase> databaseProvider) {
    return new DataModule_ProvideCategoryDaoFactory(databaseProvider);
  }

  public static CategoryDao provideCategoryDao(FinanceDatabase database) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideCategoryDao(database));
  }
}
