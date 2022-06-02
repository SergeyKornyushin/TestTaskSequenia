package com.github.sergey_kornyushin.data.database.realm

import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class RealmOperations @Inject constructor(private val config: RealmConfiguration) {


    suspend fun insertFilm() {
        val realm = Realm.getInstance(config)


    }
}