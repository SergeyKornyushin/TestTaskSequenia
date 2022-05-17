package com.github.sergey_kornyushin.domain.repository

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface SelectedFilmRepository {
    fun getSelectedFilm(film: Film): Flow<Resource<Film>>
}