package com.github.sergey_kornyushin.data.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.sergey_kornyushin.data.database.entities.Film
import com.github.sergey_kornyushin.data.database.entities.Genre


data class GenreWithFilms(
    @Embedded val genre: Genre,
    @Relation(
        parentColumn = "genreName",
        entityColumn = "filmId",
        associateBy = Junction(FilmsGenresCrossRef::class)
    )
    val films: List<Film>
)