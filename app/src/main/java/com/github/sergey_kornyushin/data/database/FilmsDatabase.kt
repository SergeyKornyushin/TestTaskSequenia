package com.github.sergey_kornyushin.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.sergey_kornyushin.data.database.dao.FilmsDao
import com.github.sergey_kornyushin.data.database.model.FilmEntity
import com.github.sergey_kornyushin.data.database.model.GenreEntity
import com.github.sergey_kornyushin.data.database.relations.FilmsGenresCrossRef

@Database(
    entities = [
        FilmEntity::class,
        GenreEntity::class,
        FilmsGenresCrossRef::class
    ],
    version = 1
)
abstract class FilmsDataBase : RoomDatabase() {

    abstract fun filmsDao(): FilmsDao

    companion object {
        val DATABASE_NAME = "films_db"

//        @Volatile
//        private var INSTANCE: FilmsDataBase? = null
//
//        fun getInstance(context: Context): FilmsDataBase {
//            synchronized(this) {
//                return INSTANCE ?: Room.databaseBuilder(
//                    context, FilmsDataBase::class.java,
//                    "films_db"
//                ).build().also {
//                    INSTANCE = it
//                }
//            }
//        }
    }
}