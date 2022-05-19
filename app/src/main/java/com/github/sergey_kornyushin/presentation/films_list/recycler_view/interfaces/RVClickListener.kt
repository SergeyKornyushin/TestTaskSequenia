package com.github.sergey_kornyushin.presentation.films_list.recycler_view.interfaces

import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem

interface RVClickListener {
    fun genreClick(genre: RVFilmItem.Genre, position: Int)
    fun filmClick(filmId: Int, filmName: String)
}