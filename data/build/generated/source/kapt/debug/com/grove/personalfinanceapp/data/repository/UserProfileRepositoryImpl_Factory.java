package com.grove.personalfinanceapp.data.repository;

import com.grove.personalfinanceapp.data.local.dao.UserProfileDao;
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
public final class UserProfileRepositoryImpl_Factory implements Factory<UserProfileRepositoryImpl> {
  private final Provider<UserProfileDao> userProfileDaoProvider;

  public UserProfileRepositoryImpl_Factory(Provider<UserProfileDao> userProfileDaoProvider) {
    this.userProfileDaoProvider = userProfileDaoProvider;
  }

  @Override
  public UserProfileRepositoryImpl get() {
    return newInstance(userProfileDaoProvider.get());
  }

  public static UserProfileRepositoryImpl_Factory create(
      Provider<UserProfileDao> userProfileDaoProvider) {
    return new UserProfileRepositoryImpl_Factory(userProfileDaoProvider);
  }

  public static UserProfileRepositoryImpl newInstance(UserProfileDao userProfileDao) {
    return new UserProfileRepositoryImpl(userProfileDao);
  }
}
