package com.github.sergey_kornyushin.data.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.sergey_kornyushin.data.database.model.FilmEntity
import com.github.sergey_kornyushin.data.database.model.FilmEntity.Companion.FILM_ID
import com.github.sergey_kornyushin.data.database.model.GenreEntity
import com.github.sergey_kornyushin.data.database.model.GenreEntity.Companion.GENRE_NAME

data class GenreWithFilms(
    @Embedded val genreEntity: GenreEntity,
    @Relation(
        parentColumn = GENRE_NAME,
        entityColumn = FILM_ID,
        associateBy = Junction(FilmsGenresCrossRef::class)
    )
    val filmEntities: List<FilmEntity>
)