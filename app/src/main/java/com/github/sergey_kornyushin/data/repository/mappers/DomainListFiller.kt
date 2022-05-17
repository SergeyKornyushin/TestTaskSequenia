package com.github.sergey_kornyushin.data.repository.mappers

import com.github.sergey_kornyushin.data.database.dao.FilmsDao
import com.github.sergey_kornyushin.presentation.main_list.recycler_view.RecyclerViewItem
import javax.inject.Inject

interface DomainListFiller {
    suspend fun createListForRecyclerView(filmsDao: FilmsDao): List<RecyclerViewItem>

    class Base @Inject constructor(private val domainMapper: DomainMapper.Base): DomainListFiller{

        private val titles: List<RecyclerViewItem.Title> = listOf(
            RecyclerViewItem.Title(1, "Жанры"),
            RecyclerViewItem.Title(2, ("Фильмы"))
        )

        override suspend fun createListForRecyclerView(filmsDao: FilmsDao): List<RecyclerViewItem> {
            val rvList: MutableList<RecyclerViewItem> = mutableListOf()
            rvList.add(titles[0])
            rvList.addAll(domainMapper.mapGenresToDomain(filmsDao.getAllGenres()))
            rvList.add(titles[1])
            rvList.addAll(domainMapper.mapFilmsToDomain(filmsDao.getAllFilms()))
            return rvList
        }
    }
}