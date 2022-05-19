package com.github.sergey_kornyushin.presentation.di

import com.github.sergey_kornyushin.domain.repository.FilmsRepository
import com.github.sergey_kornyushin.domain.repository.SelectedFilmRepository
import com.github.sergey_kornyushin.domain.repository.SortRepository
import com.github.sergey_kornyushin.domain.use_cases.GetFilmsUseCase
import com.github.sergey_kornyushin.domain.use_cases.GetSelectedFilmUseCase
import com.github.sergey_kornyushin.domain.use_cases.SortFilmsByGenreUseCase
import com.github.sergey_kornyushin.presentation.films_list.CacheGenreItem
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.SelectedPositionSaver
import com.github.sergey_kornyushin.presentation.mappers.PresenterMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    fun providePresenterMapper(): PresenterMapper =
        PresenterMapper.Base()

    @Provides
    fun provideSortFilmsByGenreUseCase(sortRepository: SortRepository): SortFilmsByGenreUseCase =
        SortFilmsByGenreUseCase.Base(sortRepository)

    @Provides
    fun provideGetSelectedFilmUseCase(selectedFilmRepository: SelectedFilmRepository): GetSelectedFilmUseCase =
        GetSelectedFilmUseCase.Base(selectedFilmRepository)


    @Provides
    fun provideGetFilmsUseCase(filmsRepository: FilmsRepository): GetFilmsUseCase =
        GetFilmsUseCase.Base(filmsRepository)

    @Singleton
    @Provides
    fun provideSelectedPositionSaver(): SelectedPositionSaver = SelectedPositionSaver(-1)

    @Provides
    fun provideCoroutineScope(): CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Main)

    @Provides
    fun provideCacheGenreItem(): CacheGenreItem = CacheGenreItem()
}