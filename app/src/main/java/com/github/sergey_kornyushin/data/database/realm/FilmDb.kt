package com.github.sergey_kornyushin.data.database.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class FilmDb(
    @PrimaryKey
    var filmId: Int = -1,
    var image_url: String = "",
    var localized_name: String = "",
    var name: String = "",
    var year: String = "",
    var rating: String = "",
    var description: String = ""
) : RealmObject()