package com.github.sergey_kornyushin.data.remote.dto

data class FilmDto(
    val id: Int? = 0,
    val genres: MutableList<String>? = mutableListOf(""),
    val image_url: String? = "",
    val localized_name: String? = "",
    val name: String? = "",
    val year: Int? = 0,
    val rating: Double? = 0.0,
    val description: String? = ""
)