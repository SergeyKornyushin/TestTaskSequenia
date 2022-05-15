package com.github.sergey_kornyushin.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmEntity(
    @PrimaryKey(autoGenerate = false)
    val filmId: Int,
    val image_url: String,
    val localized_name: String,
    val name: String,
    val year: Int,
    val rating: String,
    val description: String
)