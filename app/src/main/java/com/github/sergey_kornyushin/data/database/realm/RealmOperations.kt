package com.github.sergey_kornyushin.data.database.realm

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.Sort
import javax.inject.Inject

class RealmOperations @Inject constructor(private val realm: Realm) {

    suspend fun insertFilm(filmDb: FilmDb) {
        realm.executeTransaction { transaction ->
            transaction.insert(filmDb)
        }
    }

    suspend fun insertGenre(genreDb: GenreDb) {
        realm.executeTransaction { transaction ->
            transaction.insert(genreDb)
        }
    }

    suspend fun getAllGenres(): List<GenreDb> {
        val genresList = mutableListOf<GenreDb>()
        realm.executeTransaction { transaction ->
            transaction.where(GenreDb::class.java)
                .findAll()
                .map { genre ->
                    genresList.add(genre)
                }
        }
        return genresList
    }

    suspend fun getAllFilms(): List<FilmDb> {
        val filmsList = mutableListOf<FilmDb>()
        realm.executeTransaction { transaction ->
            transaction.where(FilmDb::class.java)
                .findAll()
                .sort("localized_name", Sort.ASCENDING)
                .map { film ->
                    filmsList.add(film)
                }
        }
        return filmsList
    }

    suspend fun clearGenresAndFilms() {
        realm.executeTransaction { transaction ->
            transaction.deleteAll()
        }
    }
}