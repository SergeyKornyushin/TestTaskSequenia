package com.github.sergey_kornyushin.presentation.films_list

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.domain.use_cases.get_films.GetFilmsUseCase
import com.github.sergey_kornyushin.domain.use_cases.sort_films.SortFilmsByGenreUseCase
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import com.github.sergey_kornyushin.presentation.mappers.PresenterMapper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FilmsListPresenter @Inject constructor(
    private val getFilmsUseCase: GetFilmsUseCase,
    private val getSortUseCase: SortFilmsByGenreUseCase,
    private val presenterMapper: PresenterMapper
) {

    fun getFilmsList() {
        getFilmsUseCase.getFilms().onEach { result ->
            when (result) {
                is Resource.Success -> {

                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        }.launchIn(scope =)
    }

    fun sortFilmsByGenre(genre: RVFilmItem.Genre) {
        getSortUseCase.getFilmsByGenre(presenterMapper.mapGenreToDomain(genre)).onEach { result ->
            when (result) {
                is Resource.Success -> {

                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        }.launchIn(scope =)
    }
}