package com.github.sergey_kornyushin.data.database.realm.mappers

import com.github.sergey_kornyushin.data.database.dao.FilmsDao
import com.github.sergey_kornyushin.data.database.realm.RealmOperations
import com.github.sergey_kornyushin.data.remote.dto.FilmsDto
import io.realm.Realm
import javax.inject.Inject

interface RealmMappersSet {
    suspend fun saveFilms(networkFilms: FilmsDto, realmOperations: RealmOperations)

    data class Base @Inject constructor(
        private val realmFilmsToDbMapper: RealmFilmsToDbMapper,
        private val realmGenresToDbMapper: RealmGenresToDbMapper
    ) : RealmMappersSet {
        override suspend fun saveFilms(networkFilms: FilmsDto, realmOperations: RealmOperations) {
            realmOperations.clearGenresAndFilms()
            realmFilmsToDbMapper.map(networkFilms).forEach { realmOperations.insertFilm(it) }
            realmGenresToDbMapper.map(networkFilms).forEach { realmOperations.insertGenre(it) }
        }
    }
}