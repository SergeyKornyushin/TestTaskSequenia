package com.github.sergey_kornyushin.data.database.realm.mappers

import com.github.sergey_kornyushin.data.database.mappers.ResponseMapper
import com.github.sergey_kornyushin.data.database.realm.GenreDb
import com.github.sergey_kornyushin.data.remote.dto.FilmsDto
import javax.inject.Inject

class RealmGenresToDbMapper @Inject constructor() : ResponseMapper<FilmsDto, List<GenreDb>> {
    override fun map(films: FilmsDto): List<GenreDb> {
        val hashSet = HashSet<String>()
        films.filmsDto.forEach { film ->
            film.genres?.forEach { genre ->
                if (genre.isNotBlank()) hashSet.add(genre)
            }
        }
        val genresList: MutableList<GenreDb> = mutableListOf()
        hashSet.forEach { genre ->
            genresList.add(GenreDb(genre))
        }
        return genresList
    }
}