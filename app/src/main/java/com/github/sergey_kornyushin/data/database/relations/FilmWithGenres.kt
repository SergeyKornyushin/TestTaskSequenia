package com.github.sergey_kornyushin.data.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.sergey_kornyushin.data.database.model.FilmEntity
import com.github.sergey_kornyushin.data.database.model.GenreEntity

data class FilmWithGenres(
    @Embedded val filmEntity: FilmEntity,
    @Relation(
        parentColumn = "filmId",
        entityColumn = "genreName",
        associateBy = Junction(FilmsGenresCrossRef::class)
    )
    val genreEntities: List<GenreEntity>
)