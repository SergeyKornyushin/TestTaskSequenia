package com.github.sergey_kornyushin.data.repository.mappers

import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.common.ResourceProvider
import com.github.sergey_kornyushin.data.database.realm.FilmDb
import com.github.sergey_kornyushin.data.database.realm.GenreDb
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import javax.inject.Inject

interface DomainListFiller {
    suspend fun createListForRecyclerView(
        genres: List<GenreDb>,
        films: List<FilmDb>
    ): List<RVFilmItem>

    class Base @Inject constructor(
        private val domainRVMapper: DomainRecyclerViewMapper.Base,
        private val resourceProvider: ResourceProvider
    ) : DomainListFiller {

        private val titles: List<RVFilmItem.Title> = listOf(
            RVFilmItem.Title(1, resourceProvider.getString(R.string.genres)),
            RVFilmItem.Title(2, (resourceProvider.getString(R.string.rv_title_films)))
        )

        override suspend fun createListForRecyclerView(
            genres: List<GenreDb>,
            films: List<FilmDb>
        ): List<RVFilmItem> {
            val rvList: MutableList<RVFilmItem> = mutableListOf()
            rvList.add(titles[0])
            rvList.addAll(domainRVMapper.mapGenresToDomain(genres))
            rvList.add(titles[1])
            rvList.addAll(domainRVMapper.mapFilmsToDomain(films))
            return rvList
        }
    }
}