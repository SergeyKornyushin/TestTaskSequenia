package com.github.sergey_kornyushin.presentation.mappers

import com.github.sergey_kornyushin.domain.model.Genre
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import javax.inject.Inject

interface PresenterMapper {
    fun mapGenreToDomain(genre: RVFilmItem.Genre): Genre

    class Base @Inject constructor() : PresenterMapper {
        override fun mapGenreToDomain(genre: RVFilmItem.Genre): Genre =
            Genre(genreName = genre.name)
    }
}