package com.github.sergey_kornyushin.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmEntity(
    @PrimaryKey(autoGenerate = false)
    val filmId: Int,
    val image_url: String,
    val localized_name: String,
    val name: String,
    val year: String,
    val rating: String,
    val description: String
){
    companion object{
        const val FILM_ID = "filmId"
    }
}