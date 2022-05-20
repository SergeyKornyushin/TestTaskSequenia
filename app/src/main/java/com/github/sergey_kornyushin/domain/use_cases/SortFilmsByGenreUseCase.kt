package com.github.sergey_kornyushin.domain.use_cases

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.domain.model.Genre
import com.github.sergey_kornyushin.domain.repository.SortRepository
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SortFilmsByGenreUseCase {
    fun getFilmsByGenre(genre: Genre): Flow<Resource<List<RVFilmItem>>>

    class Base @Inject constructor(private val sortRepository: SortRepository) :
        SortFilmsByGenreUseCase {
        override fun getFilmsByGenre(genre: Genre): Flow<Resource<List<RVFilmItem>>> {
            return sortRepository.sortFilmsByGenre(genre)
        }
    }
}