package com.github.sergey_kornyushin.presentation.main_list.recycler_view

sealed class RVFilmItem{
    data class Title(
        val id: Int,
        val title: String
    ) : RVFilmItem()

    data class Genre(
        val name: String
    ) : RVFilmItem()

    data class FilmItem(
        val id: Int,
        val image_url: String,
        val name: String,
        val localized_name: String,
    ) : RVFilmItem()
}
