package com.github.sergey_kornyushin.data.database.dao

import com.github.sergey_kornyushin.data.database.model.FilmEntity
import com.github.sergey_kornyushin.data.database.model.GenreEntity
import com.github.sergey_kornyushin.data.database.relations.FilmsGenresCrossRef
import com.github.sergey_kornyushin.data.database.relations.GenreWithFilms

class FakeFilmsDao : FilmsDao {

    override suspend fun insertFilm(filmEntity: FilmEntity) {}

    override suspend fun insertGenre(genreEntity: GenreEntity) {}

    override suspend fun insertFilmsGenreCrossRef(crossRef: FilmsGenresCrossRef) {}

    override suspend fun getGenreWithFilms(genre: String): GenreWithFilms {
        return GenreWithFilms(GenreEntity(""), listOf())
    }

    override suspend fun getAllGenres(): List<GenreEntity> {
        return listOf()
    }

    override suspend fun getAllFilms(): List<FilmEntity> {
        return listOf()
    }

    override suspend fun getFilmById(filmId: Int): FilmEntity {
        return FilmEntity(0, "", "", "", "", "", "")
    }

    override suspend fun clearFilmsTable() {}

    override suspend fun clearGenresTable() {}

    override suspend fun clearFilmsGenresCrossRefTable() {}

    override suspend fun clearAllTables(){
        clearFilmsTable()
        clearGenresTable()
        clearFilmsGenresCrossRefTable()
    }
}