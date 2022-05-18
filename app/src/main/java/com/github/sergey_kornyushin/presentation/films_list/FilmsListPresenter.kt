package com.github.sergey_kornyushin.presentation.films_list

import android.util.Log
import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.domain.use_cases.get_films.GetFilmsUseCase
import com.github.sergey_kornyushin.domain.use_cases.sort_films.SortFilmsByGenreUseCase
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import com.github.sergey_kornyushin.presentation.mappers.PresenterMapper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class FilmsListPresenter @Inject constructor(
    private val getFilmsUseCase: GetFilmsUseCase,
    private val getSortUseCase: SortFilmsByGenreUseCase,
    private val presenterMapper: PresenterMapper
) : MvpPresenter<FilmsListView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Log.i("test4", "onFirstViewAttach $attachedViews")
    }

    override fun attachView(view: FilmsListView?) {
        super.attachView(view)
        Log.i("test4", "attachView $attachedViews")
    }

    override fun detachView(view: FilmsListView?) {
        super.detachView(view)
        Log.i("test4", "attachView $attachedViews")
    }

    init {
        getFilmsList()
        Log.i("test4", "PRESENTER RECREATED")
    }

    fun getFilmsList() {
        getFilmsUseCase.getFilms().onEach { result ->
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
        }.launchIn(scope = presenterScope)
    }
}