package com.github.sergey_kornyushin.data.remote.mappers

import com.github.sergey_kornyushin.data.remote.dto.FilmDto
import com.github.sergey_kornyushin.data.database.entities.Film
import com.github.sergey_kornyushin.data.database.entities.Genre
import com.github.sergey_kornyushin.data.database.relations.FilmsGenresCrossRef

interface ResponseMapper<FILM, T> {
    fun map(films: FILM): T

    class FilmsMapper() : ResponseMapper<List<FilmDto>, List<Film>> {
        override fun map(films: List<FilmDto>) =
            films.map { film ->
                Film(
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

    class GenresMapper() : ResponseMapper<List<FilmDto>, List<Genre>> {
        override fun map(films: List<FilmDto>): List<Genre> {
            val hashSet = HashSet<String>()
            films.forEach { film ->
                film.genres?.let { hashSet.addAll(it) }
            }
            val genresList: MutableList<Genre> = mutableListOf()
            hashSet.forEach { genre ->
                genresList.add(Genre(genre))
            }
            return genresList
        }
    }

    class FilmsGenresCrossRefMapper() : ResponseMapper<List<FilmDto>, List<FilmsGenresCrossRef>> {
        override fun map(films: List<FilmDto>): List<FilmsGenresCrossRef> {
            val listFilmsGenres: MutableList<FilmsGenresCrossRef> = mutableListOf()
            films.forEach { film ->
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
}