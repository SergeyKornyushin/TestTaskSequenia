package com.github.sergey_kornyushin.domain.use_cases.get_films

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.domain.repository.FilmsRepository
import com.github.sergey_kornyushin.presentation.main_list.recycler_view.RecyclerViewItem
import kotlinx.coroutines.flow.Flow

class GetFilmsUseCase (private val filmsRepository: FilmsRepository) {
    suspend fun getFilms(): Flow<Resource<List<RecyclerViewItem>>> {
        return filmsRepository.getAndSaveFilms()
    }
}