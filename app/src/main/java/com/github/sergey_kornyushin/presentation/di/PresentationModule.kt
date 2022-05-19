package com.github.sergey_kornyushin.presentation.di

import com.github.sergey_kornyushin.domain.repository.FilmsRepository
import com.github.sergey_kornyushin.domain.repository.SelectedFilmRepository
import com.github.sergey_kornyushin.domain.repository.SortRepository
import com.github.sergey_kornyushin.domain.use_cases.get_films.GetFilmsUseCase
import com.github.sergey_kornyushin.domain.use_cases.get_selected_film.GetSelectedFilmUseCase
import com.github.sergey_kornyushin.domain.use_cases.sort_films.SortFilmsByGenreUseCase
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.SelectedPositionSaver
import com.github.sergey_kornyushin.presentation.mappers.PresenterMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Singleton
    @Provides
    fun providePresenterMapper(): PresenterMapper =
        PresenterMapper.Base()

    @Singleton
    @Provides
    fun provideSortFilmsByGenreUseCase(sortRepository: SortRepository): SortFilmsByGenreUseCase =
        SortFilmsByGenreUseCase.Base(sortRepository)

    @Singleton
    @Provides
    fun provideGetSelectedFilmUseCase(selectedFilmRepository: SelectedFilmRepository): GetSelectedFilmUseCase =
        GetSelectedFilmUseCase.Base(selectedFilmRepository)

    @Singleton
    @Provides
    fun provideGetFilmsUseCase(filmsRepository: FilmsRepository): GetFilmsUseCase =
        GetFilmsUseCase.Base(filmsRepository)

    @Singleton
    @Provides
    fun provideSelectedPositionSaver(): SelectedPositionSaver = SelectedPositionSaver(-1)
}