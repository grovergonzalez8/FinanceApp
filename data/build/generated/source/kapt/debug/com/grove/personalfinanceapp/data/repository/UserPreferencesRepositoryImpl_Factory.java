package com.grove.personalfinanceapp.data.repository;

import com.grove.personalfinanceapp.data.preferences.UserPreferencesDataStoreSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class UserPreferencesRepositoryImpl_Factory implements Factory<UserPreferencesRepositoryImpl> {
  private final Provider<UserPreferencesDataStoreSource> userPreferencesDataStoreSourceProvider;

  public UserPreferencesRepositoryImpl_Factory(
      Provider<UserPreferencesDataStoreSource> userPreferencesDataStoreSourceProvider) {
    this.userPreferencesDataStoreSourceProvider = userPreferencesDataStoreSourceProvider;
  }

  @Override
  public UserPreferencesRepositoryImpl get() {
    return newInstance(userPreferencesDataStoreSourceProvider.get());
  }

  public static UserPreferencesRepositoryImpl_Factory create(
      Provider<UserPreferencesDataStoreSource> userPreferencesDataStoreSourceProvider) {
    return new UserPreferencesRepositoryImpl_Factory(userPreferencesDataStoreSourceProvider);
  }

  public static UserPreferencesRepositoryImpl newInstance(
      UserPreferencesDataStoreSource userPreferencesDataStoreSource) {
    return new UserPreferencesRepositoryImpl(userPreferencesDataStoreSource);
  }
}
