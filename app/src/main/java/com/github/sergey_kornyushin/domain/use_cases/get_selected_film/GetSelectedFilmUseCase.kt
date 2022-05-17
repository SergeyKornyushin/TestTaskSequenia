package com.github.sergey_kornyushin.domain.use_cases.get_selected_film

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.domain.model.Film
import com.github.sergey_kornyushin.domain.repository.SelectedFilmRepository
import com.github.sergey_kornyushin.presentation.main_list.recycler_view.RecyclerViewItem
import kotlinx.coroutines.flow.Flow

class GetSelectedFilmUseCase(private val selectedFilmRepository: SelectedFilmRepository) {
    fun getSelectedFilmById(film: Film): Flow<Resource<Film>> {
        return selectedFilmRepository.getSelectedFilm(film)
    }
}