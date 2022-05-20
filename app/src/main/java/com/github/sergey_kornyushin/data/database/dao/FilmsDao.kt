package com.github.sergey_kornyushin.data.database.dao

import androidx.room.*
import com.github.sergey_kornyushin.data.database.model.FilmEntity
import com.github.sergey_kornyushin.data.database.model.GenreEntity
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
    @Query("SELECT * FROM GenreEntity WHERE genreName =:genre")
    suspend fun getGenreWithFilms(genre: String): GenreWithFilms

    @Transaction
    @Query("SELECT * FROM GenreEntity")
    suspend fun getAllGenres(): List<GenreEntity>

    @Transaction
    @Query("SELECT * FROM FilmEntity ORDER BY localized_name")
    suspend fun getAllFilms(): List<FilmEntity>

    @Transaction
    @Query("SELECT * FROM FilmEntity WHERE filmId =:filmId")
    suspend fun getFilmById(filmId: Int): FilmEntity

    @Query("DELETE FROM FilmEntity")
    suspend fun clearFilmsTable()

    @Query("DELETE FROM GenreEntity")
    suspend fun clearGenresTable()

    @Query("DELETE FROM FilmsGenresCrossRef")
    suspend fun clearFilmsGenresCrossRefTable()

    @Transaction
    suspend fun clearAllTables(){
        clearFilmsTable()
        clearGenresTable()
        clearFilmsGenresCrossRefTable()
    }
}