package com.github.sergey_kornyushin.data.database.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.sergey_kornyushin.data.database.dao.FilmsDao
import com.github.sergey_kornyushin.data.database.relations.FilmsGenresCrossRef

class FilmsDatabase {
    @Database(
        entities = [
            Film::class,
            Genre::class,
            FilmsGenresCrossRef::class
        ],
        version = 1
    )
    abstract class FilmsDataBase : RoomDatabase() {

        abstract fun filmsDao(): FilmsDao

        companion object {
            @Volatile
            private var INSTANCE: FilmsDataBase? = null

            fun getInstance(context: Context): FilmsDataBase {
                synchronized(this) {
                    return INSTANCE ?: Room.databaseBuilder(
                        context, FilmsDataBase::class.java,
                        "films_db"
                    ).build().also {
                        INSTANCE = it
                    }
                }
            }
        }
    }
}