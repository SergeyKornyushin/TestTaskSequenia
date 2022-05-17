package com.github.sergey_kornyushin.domain.repository

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.domain.model.Genre
import com.github.sergey_kornyushin.presentation.main_list.recycler_view.RVFilmItem
import kotlinx.coroutines.flow.Flow

interface SortRepository {
    fun sortFilmsByGenre(genre: Genre): Flow<Resource<List<RVFilmItem>>>
}