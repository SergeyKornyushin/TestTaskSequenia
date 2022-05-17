package com.github.sergey_kornyushin.domain.repository

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.presentation.main_list.recycler_view.RVFilmItem
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {
    fun getAndSaveFilms(): Flow<Resource<List<RVFilmItem>>>
}