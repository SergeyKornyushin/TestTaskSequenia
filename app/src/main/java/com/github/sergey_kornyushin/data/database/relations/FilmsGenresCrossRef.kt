package com.github.sergey_kornyushin.data.database.relations

import androidx.room.Entity

@Entity(primaryKeys = ["filmId", "genreName"])
data class FilmsGenresCrossRef(
    val filmId: Int,
    val genreName: String
)