// Generated by Dagger (https://dagger.dev).
package com.github.sergey_kornyushin.presentation.di;

import com.github.sergey_kornyushin.presentation.films_list.CacheGenreItem;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class PresentationModule_ProvideCacheGenreItemFactory implements Factory<CacheGenreItem> {
  @Override
  public CacheGenreItem get() {
    return provideCacheGenreItem();
  }

  public static PresentationModule_ProvideCacheGenreItemFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CacheGenreItem provideCacheGenreItem() {
    return Preconditions.checkNotNullFromProvides(PresentationModule.INSTANCE.provideCacheGenreItem());
  }

  private static final class InstanceHolder {
    private static final PresentationModule_ProvideCacheGenreItemFactory INSTANCE = new PresentationModule_ProvideCacheGenreItemFactory();
  }
}
