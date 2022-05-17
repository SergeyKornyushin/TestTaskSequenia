package com.github.sergey_kornyushin.data.database.mappers

import com.github.sergey_kornyushin.data.database.relations.FilmsGenresCrossRef
import com.github.sergey_kornyushin.data.remote.dto.FilmsDto
import javax.inject.Inject

class FilmsGenresCrossRefMapper @Inject constructor() :
    ResponseMapper<FilmsDto, List<FilmsGenresCrossRef>> {
    override fun map(films: FilmsDto): List<FilmsGenresCrossRef> {
        val listFilmsGenres: MutableList<FilmsGenresCrossRef> = mutableListOf()
        films.filmsDto.forEach { film ->
            film.genres?.forEach { genre ->
                listFilmsGenres.add(
                    FilmsGenresCrossRef(
                        filmId = film.id ?: 0,
                        genreName = genre
                    )
                )
            }
        }
        return listFilmsGenres
    }
}