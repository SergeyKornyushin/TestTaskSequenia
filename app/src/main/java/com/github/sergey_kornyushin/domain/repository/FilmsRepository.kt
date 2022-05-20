package com.github.sergey_kornyushin.domain.repository

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {
    fun getFilms(): Flow<Resource<List<RVFilmItem>>>
}