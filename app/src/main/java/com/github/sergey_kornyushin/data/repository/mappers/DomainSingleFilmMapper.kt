package com.github.sergey_kornyushin.data.repository.mappers

import com.github.sergey_kornyushin.data.database.model.FilmEntity
import com.github.sergey_kornyushin.domain.model.Film
import javax.inject.Inject

interface DomainSingleFilmMapper {
    fun mapFilmToDomain(filmDb: FilmEntity): Film

    class Base @Inject constructor() : DomainSingleFilmMapper {
        override fun mapFilmToDomain(filmDb: FilmEntity): Film =
            Film(
                filmId = filmDb.filmId,
                image_url = filmDb.image_url,
                localized_name = filmDb.localized_name,
                name = filmDb.name,
                year = filmDb.year.toString(),
                rating = filmDb.rating,
                description = filmDb.description
            )
    }
}