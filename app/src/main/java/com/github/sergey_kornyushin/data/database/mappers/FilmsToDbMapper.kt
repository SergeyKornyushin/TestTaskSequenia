package com.github.sergey_kornyushin.data.database.mappers

import com.github.sergey_kornyushin.data.database.model.FilmEntity
import com.github.sergey_kornyushin.data.remote.dto.FilmsDto
import javax.inject.Inject

class FilmsToDbMapper @Inject constructor() : ResponseMapper<FilmsDto, List<FilmEntity>> {
    override fun map(films: FilmsDto) =
        films.filmsDto.map { film ->
            FilmEntity(
                filmId = film.id ?: 0,
                image_url = film.image_url ?: "",
                localized_name = film.localized_name ?: "",
                name = film.name ?: "",
                year = film.year ?: 0,
                rating = film.rating.toString(),
                description = film.description ?: ""
            )
        }
}