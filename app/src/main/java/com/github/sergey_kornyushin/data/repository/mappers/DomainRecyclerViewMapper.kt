package com.github.sergey_kornyushin.data.repository.mappers

import com.github.sergey_kornyushin.data.database.realm.FilmDb
import com.github.sergey_kornyushin.data.database.realm.GenreDb
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import javax.inject.Inject

interface DomainRecyclerViewMapper {

    fun mapFilmsToDomain(filmsFromDb: List<FilmDb>): List<RVFilmItem>
    fun mapGenresToDomain(genresFromDb: List<GenreDb>): List<RVFilmItem>

    class Base @Inject constructor() : DomainRecyclerViewMapper {
        override fun mapFilmsToDomain(filmsFromDb: List<FilmDb>): List<RVFilmItem> =
            filmsFromDb.map { film ->
                RVFilmItem.FilmItem(
                    id = film.filmId,
                    image_url = film.image_url,
                    name = film.name,
                    localized_name = film.localized_name
                )
            }

        override fun mapGenresToDomain(genresFromDb: List<GenreDb>): List<RVFilmItem> =
            genresFromDb.map { genre ->
                RVFilmItem.Genre(name = genre.genreName)
            }
    }
}