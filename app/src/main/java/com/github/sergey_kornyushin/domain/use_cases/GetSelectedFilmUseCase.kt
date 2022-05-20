package com.github.sergey_kornyushin.domain.use_cases

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.domain.model.Film
import com.github.sergey_kornyushin.domain.repository.SelectedFilmRepository
import kotlinx.coroutines.flow.Flow

interface GetSelectedFilmUseCase {
    fun getSelectedFilmById(film: Film): Flow<Resource<Film>>

    class Base(private val selectedFilmRepository: SelectedFilmRepository) :
        GetSelectedFilmUseCase {
        override fun getSelectedFilmById(film: Film): Flow<Resource<Film>> {
            return selectedFilmRepository.getSelectedFilm(film)
        }
    }
}