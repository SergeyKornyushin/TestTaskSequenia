package com.github.sergey_kornyushin.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Genre(
    @PrimaryKey(autoGenerate = false)
    val genreName: String
)