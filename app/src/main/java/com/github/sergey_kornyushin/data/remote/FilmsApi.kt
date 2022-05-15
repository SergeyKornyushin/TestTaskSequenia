package com.github.sergey_kornyushin.data.remote

import com.github.sergey_kornyushin.data.remote.dto.FilmsDto
import retrofit2.Response
import retrofit2.http.GET

interface FilmsApi {

    @GET("/sequeniatesttask/films.json")
    suspend fun getFilms(): FilmsDto
}