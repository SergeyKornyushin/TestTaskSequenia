package com.github.sergey_kornyushin.data.database.mappers

import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.common.ResourceProvider
import com.github.sergey_kornyushin.data.extentions.avoidNullToString
import com.github.sergey_kornyushin.data.database.model.FilmEntity
import com.github.sergey_kornyushin.data.remote.dto.FilmsDto
import javax.inject.Inject

class FilmsToDbMapper @Inject constructor(private val resourceProvider: ResourceProvider) :
    ResponseMapper<FilmsDto, List<FilmEntity>> {
    override fun map(films: FilmsDto) =
        films.filmsDto.map { film ->
            FilmEntity(
                filmId = film.id ?: 0,
                image_url = film.image_url ?: "",
                localized_name = film.localized_name.avoidNullToString(resourceProvider.getString(R.string.unknown)),
                name = film.name.avoidNullToString(resourceProvider.getString(R.string.unknown)),
                year = film.year.avoidNullToString(resourceProvider.getString(R.string.unknown)),
                rating = film.rating.avoidNullToString(resourceProvider.getString(R.string.unknown)),
                description = film.description.avoidNullToString(resourceProvider.getString(R.string.unknown))
            )
        }
}