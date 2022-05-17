package com.github.sergey_kornyushin.data.database.mappers

import com.github.sergey_kornyushin.data.database.model.GenreEntity
import com.github.sergey_kornyushin.data.remote.dto.FilmsDto
import javax.inject.Inject

class GenresToDbMapper @Inject constructor() : ResponseMapper<FilmsDto, List<GenreEntity>> {
    override fun map(films: FilmsDto): List<GenreEntity> {
        val hashSet = HashSet<String>()
        films.filmsDto.forEach { film ->
            film.genres?.let { hashSet.addAll(it) }
        }
        val genresList: MutableList<GenreEntity> = mutableListOf()
        hashSet.forEach { genre ->
            genresList.add(GenreEntity(genre))
        }
        return genresList
    }
}