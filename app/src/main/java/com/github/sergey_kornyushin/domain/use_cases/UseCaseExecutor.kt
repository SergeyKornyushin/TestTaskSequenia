package com.github.sergey_kornyushin.domain.use_cases

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import kotlinx.coroutines.flow.Flow

interface UseCaseExecutor<T> {
    fun executeUseCase(useCaseResult: T)
}