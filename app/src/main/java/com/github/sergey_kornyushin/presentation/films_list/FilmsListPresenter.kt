package com.github.sergey_kornyushin.presentation.films_list

import android.util.Log
import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.domain.use_cases.UseCaseExecutor
import com.github.sergey_kornyushin.domain.use_cases.get_films.GetFilmsUseCase
import com.github.sergey_kornyushin.domain.use_cases.sort_films.SortFilmsByGenreUseCase
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import com.github.sergey_kornyushin.presentation.mappers.PresenterMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

interface FilmsListPresenter {
    fun getFilmsList()
    fun sortFilmsByGenre(genre: RVFilmItem.Genre)

    class Base @Inject constructor(
        private val getFilmsUseCase: GetFilmsUseCase,
        private val getSortUseCase: SortFilmsByGenreUseCase,
        private val presenterMapper: PresenterMapper
    ) : MvpPresenter<FilmsListView>(), UseCaseExecutor<Flow<Resource<List<RVFilmItem>>>>, FilmsListPresenter {

        init {
            getFilmsList()
            Log.i("test4", "PRESENTER RECREATED")
        }

        override fun onDestroy() {
            Log.i("test4", "PRESENTER onDestroy")
            super.onDestroy()
        }

        override fun executeUseCase(useCaseResult: Flow<Resource<List<RVFilmItem>>>) {
            useCaseResult.onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        viewState.fillRVList(result.data ?: mutableListOf())
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

        override fun getFilmsList() {
            executeUseCase(getFilmsUseCase.getFilms())
        }

        override fun sortFilmsByGenre(genre: RVFilmItem.Genre) {
            executeUseCase(getSortUseCase.getFilmsByGenre(presenterMapper.mapGenreToDomain(genre)))
        }
    }
}








