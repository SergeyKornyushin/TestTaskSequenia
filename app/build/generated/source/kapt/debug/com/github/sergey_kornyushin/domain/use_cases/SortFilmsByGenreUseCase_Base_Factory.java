// Generated by Dagger (https://dagger.dev).
package com.github.sergey_kornyushin.domain.use_cases;

import com.github.sergey_kornyushin.domain.repository.SortRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SortFilmsByGenreUseCase_Base_Factory implements Factory<SortFilmsByGenreUseCase.Base> {
  private final Provider<SortRepository> sortRepositoryProvider;

  public SortFilmsByGenreUseCase_Base_Factory(Provider<SortRepository> sortRepositoryProvider) {
    this.sortRepositoryProvider = sortRepositoryProvider;
  }

  @Override
  public SortFilmsByGenreUseCase.Base get() {
    return newInstance(sortRepositoryProvider.get());
  }

  public static SortFilmsByGenreUseCase_Base_Factory create(
      Provider<SortRepository> sortRepositoryProvider) {
    return new SortFilmsByGenreUseCase_Base_Factory(sortRepositoryProvider);
  }

  public static SortFilmsByGenreUseCase.Base newInstance(SortRepository sortRepository) {
    return new SortFilmsByGenreUseCase.Base(sortRepository);
  }
}