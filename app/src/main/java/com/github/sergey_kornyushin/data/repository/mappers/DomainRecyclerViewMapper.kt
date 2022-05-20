package com.github.sergey_kornyushin.data.repository.mappers

import com.github.sergey_kornyushin.data.database.model.FilmEntity
import com.github.sergey_kornyushin.data.database.model.GenreEntity
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import javax.inject.Inject

interface DomainRecyclerViewMapper {

    fun mapFilmsToDomain(filmsFromDb: List<FilmEntity>): List<RVFilmItem>
    fun mapGenresToDomain(genresFromDb: List<GenreEntity>): List<RVFilmItem>

    class Base @Inject constructor() : DomainRecyclerViewMapper {
        override fun mapFilmsToDomain(filmsFromDb: List<FilmEntity>): List<RVFilmItem> =
            filmsFromDb.map { film ->
                RVFilmItem.FilmItem(
                    id = film.filmId,
                    image_url = film.image_url,
                    name = film.name,
                    localized_name = film.localized_name
                )
            }

        override fun mapGenresToDomain(genresFromDb: List<GenreEntity>): List<RVFilmItem> =
            genresFromDb.map { genre ->
                RVFilmItem.Genre(name = genre.genreName)
            }
    }
}