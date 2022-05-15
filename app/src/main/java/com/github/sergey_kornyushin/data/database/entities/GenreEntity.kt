package com.github.sergey_kornyushin.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenreEntity(
    @PrimaryKey(autoGenerate = false)
    val genreName: String
)