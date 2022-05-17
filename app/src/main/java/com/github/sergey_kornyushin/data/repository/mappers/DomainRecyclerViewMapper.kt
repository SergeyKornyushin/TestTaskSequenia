package com.github.sergey_kornyushin.data.repository.mappers

import com.github.sergey_kornyushin.data.database.model.FilmEntity
import com.github.sergey_kornyushin.data.database.model.GenreEntity
import com.github.sergey_kornyushin.presentation.main_list.recycler_view.RecyclerViewItem
import javax.inject.Inject

interface DomainRecyclerViewMapper {

    fun mapFilmsToDomain(filmsFromDb: List<FilmEntity>): List<RecyclerViewItem>
    fun mapGenresToDomain(genresFromDb: List<GenreEntity>): List<RecyclerViewItem>

    class Base @Inject constructor() : DomainRecyclerViewMapper {
        override fun mapFilmsToDomain(filmsFromDb: List<FilmEntity>): List<RecyclerViewItem> =
            filmsFromDb.map { film ->
                RecyclerViewItem.FilmItem(
                    id = film.filmId,
                    image_url = film.image_url,
                    name = film.name,
                    localized_name = film.localized_name
                )
            }

        override fun mapGenresToDomain(genresFromDb: List<GenreEntity>): List<RecyclerViewItem> =
            genresFromDb.map { genre ->
                RecyclerViewItem.Genre(name = genre.genreName)
            }
    }
}