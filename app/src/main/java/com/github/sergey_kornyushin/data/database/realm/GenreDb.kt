package com.github.sergey_kornyushin.data.database.realm

import io.realm.annotations.PrimaryKey


open class GenreDb(
    @PrimaryKey
    val genreName: String,
    var isSelected: Boolean
) {
}