package com.github.sergey_kornyushin.presentation.films_list.recycler_view

interface RVClickListener {
    fun genreClick(genre: RVFilmItem.Genre, position: Int)
    fun filmClick(filmId: Int, filmName: String)
}