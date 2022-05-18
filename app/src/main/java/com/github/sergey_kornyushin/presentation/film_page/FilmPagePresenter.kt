package com.github.sergey_kornyushin.presentation.film_page

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.domain.use_cases.get_films.GetFilmsUseCase
import com.github.sergey_kornyushin.domain.use_cases.sort_films.SortFilmsByGenreUseCase
import com.github.sergey_kornyushin.presentation.films_list.FilmsListView
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import com.github.sergey_kornyushin.presentation.mappers.PresenterMapper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class FilmPagePresenter@Inject constructor(

) : MvpPresenter<FilmPageView>() {

//    fun sortFilmsByGenre(genre: RVFilmItem.Genre) {
//        getSortUseCase.getFilmsByGenre(presenterMapper.mapGenreToDomain(genre)).onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//
//                }
//                is Resource.Error -> {
//
//                }
//                is Resource.Loading -> {
//
//                }
//            }
//        }.launchIn(scope = presenterScope)
//    }
}