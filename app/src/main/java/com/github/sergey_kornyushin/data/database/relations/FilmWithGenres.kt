package com.github.sergey_kornyushin.data.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.sergey_kornyushin.data.database.entities.Film
import com.github.sergey_kornyushin.data.database.entities.Genre

data class FilmWithGenres(
    @Embedded val film: Film,
    @Relation(
        parentColumn = "filmId",
        entityColumn = "genreName",
        associateBy = Junction(FilmsGenresCrossRef::class)
    )
    val genres: List<Genre>
)