package com.github.sergey_kornyushin.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenreEntity(
    @PrimaryKey(autoGenerate = false)
    val genreName: String
){
    companion object{
        const val GENRE_NAME = "genreName"
    }
}