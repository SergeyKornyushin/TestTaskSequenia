package com.github.sergey_kornyushin.data.database.relations

import androidx.room.Entity
import com.github.sergey_kornyushin.data.database.model.FilmEntity.Companion.FILM_ID
import com.github.sergey_kornyushin.data.database.model.GenreEntity.Companion.GENRE_NAME

@Entity(primaryKeys = [FILM_ID, GENRE_NAME])
data class FilmsGenresCrossRef(
    val filmId: Int,
    val genreName: String
)