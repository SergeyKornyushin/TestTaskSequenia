package com.github.sergey_kornyushin.data.repository

import com.github.sergey_kornyushin.data.database.FilmsDataBase
import com.github.sergey_kornyushin.data.database.mappers.FilmsGenresCrossRefMapper
import com.github.sergey_kornyushin.data.database.mappers.FilmsToDbMapper
import com.github.sergey_kornyushin.data.database.mappers.GenresToDbMapper
import com.github.sergey_kornyushin.data.remote.dto.FilmsDto
import javax.inject.Inject

interface DbRepository {
    suspend fun insertFilm()
    suspend fun insertGenre()
    suspend fun insertFilmsGenresCrossRef()

    class Base @Inject constructor(
        private val db: FilmsDataBase,
        private val films: FilmsDto
    ) : DbRepository {

        private val dao = db.filmsDao()

        override suspend fun insertFilm() {
            val filmEntity = FilmsToDbMapper().map(films)
            filmEntity.forEach { film ->
                dao.insertFilm(film)
            }
        }

        override suspend fun insertGenre() {
            val genreEntity = GenresToDbMapper().map(films)
            genreEntity.forEach { genre ->
                dao.insertGenre(genre)
            }
        }

        override suspend fun insertFilmsGenresCrossRef() {
            val filmsGenresCrossRef = FilmsGenresCrossRefMapper().map(films)
            filmsGenresCrossRef.forEach { crossRef ->
                dao.insertFilmsGenreCrossRef(crossRef)
            }
        }
    }
}