package com.github.sergey_kornyushin.data.database.dao

import androidx.room.*
import com.github.sergey_kornyushin.data.database.entities.Film
import com.github.sergey_kornyushin.data.database.entities.Genre
import com.github.sergey_kornyushin.data.database.relations.FilmWithGenres
import com.github.sergey_kornyushin.data.database.relations.FilmsGenresCrossRef
import com.github.sergey_kornyushin.data.database.relations.GenreWithFilms

@Dao
interface FilmsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: Film)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: Genre)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilmsGenreCrossRef(crossRef: FilmsGenresCrossRef)

    @Transaction
    @Query("SELECT * FROM Film WHERE filmId =:film")
    suspend fun getFilmWithGenres(film: String): FilmWithGenres

    @Transaction
    @Query("SELECT * FROM genre WHERE genreName =:genre")
    suspend fun getGenreWithFilms(genre: String): GenreWithFilms

    @Transaction
    @Query("SELECT * FROM Genre")
    suspend fun getAllGenres(): List<Genre>

    @Transaction
    @Query("SELECT * FROM Film")
    suspend fun getAllFilms(): List<Film>
}