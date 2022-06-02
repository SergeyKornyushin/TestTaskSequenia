package com.github.sergey_kornyushin.data.database.realm


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class FilmDb(
    @PrimaryKey
    val filmId: Int,
    val image_url: String,
    val localized_name: String,
    val name: String,
    val year: String,
    val rating: String,
    val description: String
) : RealmObject()