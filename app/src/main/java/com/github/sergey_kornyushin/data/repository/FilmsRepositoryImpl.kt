package com.github.sergey_kornyushin.data.repository

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.data.database.dao.FilmsDao
import com.github.sergey_kornyushin.data.database.mappers.MappersSet
import com.github.sergey_kornyushin.data.remote.FilmsApi
import com.github.sergey_kornyushin.data.repository.mappers.DomainListFiller
import com.github.sergey_kornyushin.domain.model.Genre
import com.github.sergey_kornyushin.domain.repository.FilmsRepository
import com.github.sergey_kornyushin.domain.repository.SortRepository
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.NullPointerException
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val filmsDao: FilmsDao,
    private val api: FilmsApi,
    private val mappers: MappersSet.Base,
    private val listFiller: DomainListFiller
) : FilmsRepository, SortRepository {

    override fun getAndSaveFilms(): Flow<Resource<List<RVFilmItem>>> = flow {
        try {
            emit(Resource.Loading())
            mappers.saveFilms(api.getFilms(), filmsDao)
            emit(
                Resource.Success(
                    listFiller.createListForRecyclerView(
                        filmsDao.getAllGenres(),
                        filmsDao.getAllFilms()
                    )
                )
            )
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

    override fun sortFilmsByGenre(genre: Genre): Flow<Resource<List<RVFilmItem>>> = flow {
        try {
            emit(Resource.Loading())
            emit(
                Resource.Success(
                    listFiller.createListForRecyclerView(
                        filmsDao.getAllGenres(),
                        filmsDao.getGenreWithFilms(genre.genreName).filmEntities
                    )
                )
            )
        } catch (e: NullPointerException) {
            emit(Resource.Error("Unexpected error"))
        }
    }
}