package com.github.sergey_kornyushin.presentation.films_list

import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.common.ResourceProvider
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
import java.text.FieldPosition
import javax.inject.Inject

interface FilmsListPresenter {
    fun getFilmsList()
    fun sortFilmsByGenre(genre: RVFilmItem.Genre, position: Int)

    class Base @Inject constructor(
        private val getFilmsUseCase: GetFilmsUseCase,
        private val getSortUseCase: SortFilmsByGenreUseCase,
        private val presenterMapper: PresenterMapper,
        private val resourceProvider: ResourceProvider
    ) : MvpPresenter<FilmsListView>(), UseCaseExecutor<Flow<Resource<List<RVFilmItem>>>>,
        FilmsListPresenter {

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
                            result.message ?: resourceProvider.getString(R.string.unexpected_error)
                        )
                        viewState.showLoading(false)
                    }
                    is Resource.Loading -> {
                        viewState.showLoading(true)
                    }
                }
            }.launchIn(scope = presenterScope)
        }

        init {
            getFilmsList()
        }

        override fun getFilmsList() {
            executeUseCase(getFilmsUseCase.getFilms())
        }

        override fun sortFilmsByGenre(genre: RVFilmItem.Genre, position: Int) {
            executeUseCase(getSortUseCase.getFilmsByGenre(presenterMapper.mapGenreToDomain(genre)))
        }
    }
}