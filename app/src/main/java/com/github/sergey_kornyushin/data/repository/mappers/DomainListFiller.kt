package com.github.sergey_kornyushin.data.repository.mappers

import com.github.sergey_kornyushin.data.database.model.FilmEntity
import com.github.sergey_kornyushin.data.database.model.GenreEntity
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import javax.inject.Inject

interface DomainListFiller {
    suspend fun createListForRecyclerView(genres: List<GenreEntity>, films: List<FilmEntity>): List<RVFilmItem>

    class Base @Inject constructor(private val domainRVMapper: DomainRecyclerViewMapper.Base) :
        DomainListFiller {

        private val titles: List<RVFilmItem.Title> = listOf(
            RVFilmItem.Title(1, "Жанры"),
            RVFilmItem.Title(2, ("Фильмы"))
        )

        override suspend fun createListForRecyclerView(genres: List<GenreEntity>, films: List<FilmEntity>): List<RVFilmItem> {
            val rvList: MutableList<RVFilmItem> = mutableListOf()
            rvList.add(titles[0])
            rvList.addAll(domainRVMapper.mapGenresToDomain(genres))
            rvList.add(titles[1])
            rvList.addAll(domainRVMapper.mapFilmsToDomain(films))
            return rvList
        }
    }
}