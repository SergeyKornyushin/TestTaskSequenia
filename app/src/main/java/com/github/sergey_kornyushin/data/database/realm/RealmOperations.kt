package com.github.sergey_kornyushin.data.database.realm

import com.github.sergey_kornyushin.domain.model.Genre
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.Sort
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import okhttp3.Dispatcher
import javax.inject.Inject

class RealmOperations @Inject constructor(private val realmConfiguration: RealmConfiguration) {

    suspend fun insertFilm(filmDb: FilmDb) {
        val realm = Realm.getInstance(realmConfiguration)

        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
            transaction.insert(filmDb)
        }
    }

    suspend fun insertGenre(genreDb: GenreDb) {
        val realm = Realm.getInstance(realmConfiguration)

        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
            transaction.insert(genreDb)
        }
    }

    suspend fun getAllGenres(): List<GenreDb> {
        val realm = Realm.getInstance(realmConfiguration)
        val genresList = mutableListOf<GenreDb>()

        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
            transaction.where(GenreDb::class.java)
                .findAll()
                .map { genre ->
                    genresList.add(genre)
                }
        }
        return genresList
    }

    suspend fun getAllFilms(): List<FilmDb> {
        val realm = Realm.getInstance(realmConfiguration)
        val filmsList = mutableListOf<FilmDb>()

        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
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
        val realm = Realm.getInstance(realmConfiguration)

        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
            transaction.deleteAll()
        }
    }

    suspend fun getGenreWithFilms(): List<FilmDb> {
//        val realm = Realm.getInstance(realmConfiguration)
//        val filmsList = mutableListOf<FilmDb>()
//
//        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
//            transaction.where(GenreDb::class.java)
//                .findAll()
//                .sort()
//        }
        return emptyList()
    }

    suspend fun getFilmById(filmId: Int): FilmDb? {
        val realm = Realm.getInstance(realmConfiguration)
        var film: FilmDb? = FilmDb()
        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
            film = transaction.where(FilmDb::class.java)
                .equalTo("filmId", filmId)
                .findFirst()
        }
        return film
    }

}