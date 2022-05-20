package com.github.sergey_kornyushin.data.remote.dto

import com.google.gson.annotations.SerializedName

data class FilmsDto(
    @SerializedName("films")
    val filmsDto: MutableList<FilmDto>
)