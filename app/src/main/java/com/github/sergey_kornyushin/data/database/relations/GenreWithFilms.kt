package com.github.sergey_kornyushin.data.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.sergey_kornyushin.data.database.model.FilmEntity
import com.github.sergey_kornyushin.data.database.model.GenreEntity


data class GenreWithFilms(
    @Embedded val genreEntity: GenreEntity,
    @Relation(
        parentColumn = "genreName",
        entityColumn = "filmId",
        associateBy = Junction(FilmsGenresCrossRef::class)
    )
    val filmEntities: List<FilmEntity>
)