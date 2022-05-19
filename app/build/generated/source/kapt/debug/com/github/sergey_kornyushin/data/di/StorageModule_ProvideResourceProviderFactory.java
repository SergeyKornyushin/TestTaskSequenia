// Generated by Dagger (https://dagger.dev).
package com.github.sergey_kornyushin.data.di;

import android.content.Context;
import com.github.sergey_kornyushin.common.ResourceProvider;
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
public final class StorageModule_ProvideResourceProviderFactory implements Factory<ResourceProvider> {
  private final Provider<Context> contextProvider;

  public StorageModule_ProvideResourceProviderFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ResourceProvider get() {
    return provideResourceProvider(contextProvider.get());
  }

  public static StorageModule_ProvideResourceProviderFactory create(
      Provider<Context> contextProvider) {
    return new StorageModule_ProvideResourceProviderFactory(contextProvider);
  }

  public static ResourceProvider provideResourceProvider(Context context) {
    return Preconditions.checkNotNullFromProvides(StorageModule.INSTANCE.provideResourceProvider(context));
  }
}