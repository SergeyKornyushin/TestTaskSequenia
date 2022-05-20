package com.github.sergey_kornyushin.presentation.films_list

import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.common.ResourceProvider
import com.github.sergey_kornyushin.domain.use_cases.UseCaseExecutor
import com.github.sergey_kornyushin.domain.use_cases.GetFilmsUseCase
import com.github.sergey_kornyushin.domain.use_cases.SortFilmsByGenreUseCase
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import com.github.sergey_kornyushin.presentation.mappers.PresenterMapper
import kotlinx.coroutines.*
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
        private val presenterMapper: PresenterMapper,
        private val resourceProvider: ResourceProvider,
        private var coroutineScope: CoroutineScope,
        private val cacheGenreItem: CacheGenreItem
    ) : MvpPresenter<FilmsListView>(), UseCaseExecutor<Flow<Resource<List<RVFilmItem>>>>,
        FilmsListPresenter {

        init {
            coroutineScope = presenterScope
            getFilmsList()
        }

        override fun executeUseCase(useCaseResult: Flow<Resource<List<RVFilmItem>>>) {
            useCaseResult.onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        val mutableList: MutableList<RVFilmItem> = mutableListOf()
                        mutableList.addAll(result.data ?: mutableList)
                        viewState.fillRVList(mutableList)
                        viewState.showLoading(false)
                    }
                    is Resource.Error -> {
                        viewState.showError(
                            result.message
                                ?: resourceProvider.getString(R.string.unexpected_error)
                        )
                        viewState.showLoading(false)
                    }
                    is Resource.Loading -> {
                        viewState.showLoading(true)
                    }
                }
            }.launchIn(scope = coroutineScope)
        }

        override fun getFilmsList() {
            executeUseCase(getFilmsUseCase.getFilms())
        }

        override fun sortFilmsByGenre(genre: RVFilmItem.Genre) {
            coroutineScope.coroutineContext.job.cancelChildren()
            if (cacheGenreItem.genreName == genre.name) {
                executeUseCase(getFilmsUseCase.getFilms())
                cacheGenreItem.genreName = BLANK_GENRE
            } else {
                executeUseCase(getSortUseCase.getFilmsByGenre(presenterMapper.mapGenreToDomain(genre)))
                cacheGenreItem.genreName = genre.name
            }
        }
    }

    private companion object {
        private const val BLANK_GENRE = ""
    }
}