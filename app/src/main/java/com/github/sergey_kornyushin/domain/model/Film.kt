package com.github.sergey_kornyushin.domain.model

data class Film(
    val filmId: Int,
    val image_url: String = "",
    val localized_name: String = "",
    val name: String = "",
    val year: Int = 0,
    val rating: String = "",
    val description: String = ""
)