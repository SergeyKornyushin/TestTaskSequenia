package com.github.sergey_kornyushin.presentation.film_page

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.domain.model.Film
import com.github.sergey_kornyushin.domain.use_cases.UseCaseExecutor
import com.github.sergey_kornyushin.domain.use_cases.get_films.GetFilmsUseCase
import com.github.sergey_kornyushin.domain.use_cases.get_selected_film.GetSelectedFilmUseCase
import com.github.sergey_kornyushin.domain.use_cases.sort_films.SortFilmsByGenreUseCase
import com.github.sergey_kornyushin.presentation.films_list.FilmsListView
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import com.github.sergey_kornyushin.presentation.mappers.PresenterMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

interface FilmPagePresenter {
    fun getFilm(filmId: Int)

    class Base @Inject constructor(private val getSelectedFilmUseCase: GetSelectedFilmUseCase) :
        MvpPresenter<FilmPageView>(), FilmPagePresenter, UseCaseExecutor<Flow<Resource<Film>>> {

        override fun getFilm(filmId: Int) {
            executeUseCase(getSelectedFilmUseCase.getSelectedFilmById(Film(filmId = filmId)))
        }

        override fun executeUseCase(useCaseResult: Flow<Resource<Film>>) {
            useCaseResult.onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        viewState.showFilm(
                            result.data ?: Film(
                                0,
                                localized_name = "Unexpected error"
                            )
                        )
                        viewState.showLoading(false)
                    }
                    is Resource.Error -> {
                        viewState.showError(result.message ?: "Unexpected error")
                        viewState.showLoading(false)
                    }
                    is Resource.Loading -> {
                        viewState.showLoading(true)
                    }
                }
            }.launchIn(scope = presenterScope)
        }
    }
}