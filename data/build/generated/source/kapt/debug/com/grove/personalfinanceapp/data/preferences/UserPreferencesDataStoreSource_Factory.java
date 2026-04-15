package com.grove.personalfinanceapp.data.preferences;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class UserPreferencesDataStoreSource_Factory implements Factory<UserPreferencesDataStoreSource> {
  private final Provider<DataStore<Preferences>> dataStoreProvider;

  public UserPreferencesDataStoreSource_Factory(
      Provider<DataStore<Preferences>> dataStoreProvider) {
    this.dataStoreProvider = dataStoreProvider;
  }

  @Override
  public UserPreferencesDataStoreSource get() {
    return newInstance(dataStoreProvider.get());
  }

  public static UserPreferencesDataStoreSource_Factory create(
      Provider<DataStore<Preferences>> dataStoreProvider) {
    return new UserPreferencesDataStoreSource_Factory(dataStoreProvider);
  }

  public static UserPreferencesDataStoreSource newInstance(DataStore<Preferences> dataStore) {
    return new UserPreferencesDataStoreSource(dataStore);
  }
}
