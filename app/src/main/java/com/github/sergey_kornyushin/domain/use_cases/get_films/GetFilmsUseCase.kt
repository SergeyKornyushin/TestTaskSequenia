package com.github.sergey_kornyushin.domain.use_cases.get_films

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.domain.repository.FilmsRepository
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetFilmsUseCase {
    fun getFilms(): Flow<Resource<List<RVFilmItem>>>


    class Base @Inject constructor(private val filmsRepository: FilmsRepository) : GetFilmsUseCase {
        override fun getFilms(): Flow<Resource<List<RVFilmItem>>> {
            return filmsRepository.getAndSaveFilms()
        }
    }
}