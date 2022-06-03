package com.github.sergey_kornyushin.presentation.extentions

import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem

fun List<RVFilmItem>.filmsStartsFromEven(): Boolean {
    var genresCount = 0
    this.forEach { item ->
        if (item is RVFilmItem.Genre) {
            genresCount++
        }
        if (item is RVFilmItem.FilmItem) return@forEach
    }
    return genresCount % 2 == 0
}
