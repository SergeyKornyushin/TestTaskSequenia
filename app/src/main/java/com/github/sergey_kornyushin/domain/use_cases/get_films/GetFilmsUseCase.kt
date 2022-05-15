package com.github.sergey_kornyushin.domain.use_cases.get_films

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.data.repository.ApiRepository
import com.github.sergey_kornyushin.domain.model.Film
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(
    private val repository: ApiRepository
) {

}