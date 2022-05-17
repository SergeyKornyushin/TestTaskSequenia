package com.github.sergey_kornyushin.data.database.mappers

import com.github.sergey_kornyushin.data.database.dao.FilmsDao
import com.github.sergey_kornyushin.data.remote.dto.FilmsDto
import javax.inject.Inject

interface MappersSet {
    suspend fun saveFilms(networkFilms: FilmsDto, filmsDao: FilmsDao)

    data class Base @Inject constructor(
        private val filmsToDbMapper: FilmsToDbMapper,
        private val genresToDbMapper: GenresToDbMapper,
        private val filmsGenresCrossRefMapper: FilmsGenresCrossRefMapper
    ) : MappersSet {
        override suspend fun saveFilms(
            networkFilms: FilmsDto,
            filmsDao: FilmsDao
        ) {
            filmsDao.clearAllTables()
            filmsToDbMapper.map(networkFilms).forEach { filmsDao.insertFilm(it) }
            genresToDbMapper.map(networkFilms).forEach { filmsDao.insertGenre(it) }
            filmsGenresCrossRefMapper.map(networkFilms)
                .forEach { filmsDao.insertFilmsGenreCrossRef(it) }
        }
    }
}