package com.github.sergey_kornyushin.domain.use_cases.sort_films

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.domain.model.Genre
import com.github.sergey_kornyushin.domain.repository.SortRepository
import com.github.sergey_kornyushin.presentation.main_list.recycler_view.RVFilmItem
import kotlinx.coroutines.flow.Flow

class SortFilmsByGenreUseCase(private val sortRepository: SortRepository) {
    fun getFilmsByGenre(genre: Genre): Flow<Resource<List<RVFilmItem>>> {
        return sortRepository.sortFilmsByGenre(genre)
    }
}