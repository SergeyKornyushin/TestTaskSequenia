package com.github.sergey_kornyushin.domain.repository

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.presentation.main_list.recycler_view.RecyclerViewItem
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {
    suspend fun getAndSaveFilms(): Flow<Resource<List<RecyclerViewItem>>>
}