package com.github.sergey_kornyushin.data.database.dao

import androidx.room.*
import com.github.sergey_kornyushin.data.database.entities.FilmEntity
import com.github.sergey_kornyushin.data.database.entities.GenreEntity
import com.github.sergey_kornyushin.data.database.relations.FilmWithGenres
import com.github.sergey_kornyushin.data.database.relations.FilmsGenresCrossRef
import com.github.sergey_kornyushin.data.database.relations.GenreWithFilms

@Dao
interface FilmsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(filmEntity: FilmEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genreEntity: GenreEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilmsGenreCrossRef(crossRef: FilmsGenresCrossRef)

    @Transaction
    @Query("SELECT * FROM FilmEntity WHERE filmId =:film")
    suspend fun getFilmWithGenres(film: String): FilmWithGenres

    @Transaction
    @Query("SELECT * FROM GenreEntity WHERE genreName =:genre")
    suspend fun getGenreWithFilms(genre: String): GenreWithFilms

    @Transaction
    @Query("SELECT * FROM GenreEntity")
    suspend fun getAllGenres(): List<GenreEntity>

    @Transaction
    @Query("SELECT * FROM FilmEntity")
    suspend fun getAllFilms(): List<FilmEntity>
}