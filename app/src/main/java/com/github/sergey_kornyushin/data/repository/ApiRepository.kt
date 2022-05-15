package com.github.sergey_kornyushin.data.repository

import com.github.sergey_kornyushin.data.remote.FilmsApi
import com.github.sergey_kornyushin.data.remote.dto.FilmDto
import javax.inject.Inject

interface ApiRepository {

    suspend fun getFilms(): List<FilmDto>

    class Base @Inject constructor(
        private val api: FilmsApi
    ) : ApiRepository {

        override suspend fun getFilms() =
            api.getFilms().filmsDto
    }
}