package com.github.sergey_kornyushin.data.repository

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.data.database.dao.FilmsDao
import com.github.sergey_kornyushin.data.database.mappers.FilmsGenresCrossRefMapper
import com.github.sergey_kornyushin.data.database.mappers.FilmsToDbMapper
import com.github.sergey_kornyushin.data.database.mappers.GenresToDbMapper
import com.github.sergey_kornyushin.data.remote.FilmsApi
import com.github.sergey_kornyushin.domain.model.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface DataRepository {
    suspend fun getAndSaveFilms(): Flow<Resource<List<Film>>>

    class Base(
        private val filmsDao: FilmsDao,
        private val api: FilmsApi
    ) : DataRepository {

        @Inject
        lateinit var filmsMapper: FilmsToDbMapper

        @Inject
        lateinit var genresMapper: GenresToDbMapper

        @Inject
        lateinit var filmsGenresCrossRefMapper: FilmsGenresCrossRefMapper

        override suspend fun getAndSaveFilms(): Flow<Resource<List<Film>>> = flow {
            emit(Resource.Loading())
            try {
                val networkFilms = api.getFilms()
                val filmsEntity = filmsMapper.map(networkFilms)
                filmsEntity.forEach { filmsDao.insertFilm(it) }
                val genresEntity = genresMapper.map(networkFilms)
                genresEntity.forEach { filmsDao.insertGenre(it) }
                val filmsGenresEntity = filmsGenresCrossRefMapper.map(networkFilms)
                filmsGenresEntity.forEach { filmsDao.insertFilmsGenreCrossRef(it) }
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection"))
            }

        }


    }

}