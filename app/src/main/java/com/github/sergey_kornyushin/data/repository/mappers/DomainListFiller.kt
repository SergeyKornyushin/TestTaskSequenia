package com.github.sergey_kornyushin.data.repository.mappers

import com.github.sergey_kornyushin.data.database.model.FilmEntity
import com.github.sergey_kornyushin.data.database.model.GenreEntity
import com.github.sergey_kornyushin.presentation.main_list.recycler_view.RecyclerViewItem
import javax.inject.Inject

interface DomainListFiller {
    suspend fun createListForRecyclerView(genres: List<GenreEntity>, films: List<FilmEntity>): List<RecyclerViewItem>

    class Base @Inject constructor(private val domainRVMapper: DomainRecyclerViewMapper.Base) :
        DomainListFiller {

        private val titles: List<RecyclerViewItem.Title> = listOf(
            RecyclerViewItem.Title(1, "Жанры"),
            RecyclerViewItem.Title(2, ("Фильмы"))
        )

        override suspend fun createListForRecyclerView(genres: List<GenreEntity>, films: List<FilmEntity>): List<RecyclerViewItem> {
            val rvList: MutableList<RecyclerViewItem> = mutableListOf()
            rvList.add(titles[0])
            rvList.addAll(domainRVMapper.mapGenresToDomain(genres))
            rvList.add(titles[1])
            rvList.addAll(domainRVMapper.mapFilmsToDomain(films))
            return rvList
        }
    }
}