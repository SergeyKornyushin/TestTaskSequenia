package com.github.sergey_kornyushin.data.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.sergey_kornyushin.data.database.model.FilmEntity
import com.github.sergey_kornyushin.data.database.model.FilmEntity.Companion.FILM_ID
import com.github.sergey_kornyushin.data.database.model.GenreEntity
import com.github.sergey_kornyushin.data.database.model.GenreEntity.Companion.GENRE_NAME

data class FilmWithGenres(
    @Embedded val filmEntity: FilmEntity,
    @Relation(
        parentColumn = FILM_ID,
        entityColumn = GENRE_NAME,
        associateBy = Junction(FilmsGenresCrossRef::class)
    )
    val genreEntities: List<GenreEntity>
)