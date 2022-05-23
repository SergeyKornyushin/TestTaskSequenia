package com.github.sergey_kornyushin.data.remote

import com.github.sergey_kornyushin.data.remote.dto.FilmsDto
import retrofit2.http.GET

class FakeFilmsApi : FilmsApi {

    override suspend fun getFilms(): FilmsDto {
        return FilmsDto(mutableListOf())
    }

}