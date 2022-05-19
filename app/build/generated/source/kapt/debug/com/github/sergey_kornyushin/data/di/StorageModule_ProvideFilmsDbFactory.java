// Generated by Dagger (https://dagger.dev).
package com.github.sergey_kornyushin.data.di;

import android.content.Context;
import com.github.sergey_kornyushin.data.database.FilmsDataBase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class StorageModule_ProvideFilmsDbFactory implements Factory<FilmsDataBase> {
  private final Provider<Context> contextProvider;

  public StorageModule_ProvideFilmsDbFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public FilmsDataBase get() {
    return provideFilmsDb(contextProvider.get());
  }

  public static StorageModule_ProvideFilmsDbFactory create(Provider<Context> contextProvider) {
    return new StorageModule_ProvideFilmsDbFactory(contextProvider);
  }

  public static FilmsDataBase provideFilmsDb(Context context) {
    return Preconditions.checkNotNullFromProvides(StorageModule.INSTANCE.provideFilmsDb(context));
  }
}