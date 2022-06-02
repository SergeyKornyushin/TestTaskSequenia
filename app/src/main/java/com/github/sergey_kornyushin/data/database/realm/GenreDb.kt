package com.github.sergey_kornyushin.data.database.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey


open class GenreDb(
    @PrimaryKey
    var genreName: String = "",
    var isSelected: Boolean = false,
    var films: RealmList<FilmDb> = RealmList()
) : RealmObject()