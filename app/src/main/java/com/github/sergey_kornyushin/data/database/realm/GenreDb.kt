package com.github.sergey_kornyushin.data.database.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class GenreDb(
    @PrimaryKey
    var genreName: String = "",
    var isSelected: Boolean = false
) : RealmObject()