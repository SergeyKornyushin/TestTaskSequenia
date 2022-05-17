package com.github.sergey_kornyushin.data.di

import android.content.Context
import androidx.room.Room
import com.github.sergey_kornyushin.data.database.FilmsDataBase
import com.github.sergey_kornyushin.data.database.dao.FilmsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Singleton
    @Provides
    fun provideFilmsDb(@ApplicationContext context: Context): FilmsDataBase =
        Room.databaseBuilder(
            context,
            FilmsDataBase::class.java,
            FilmsDataBase.DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideFilmsDao(filmsDataBase: FilmsDataBase): FilmsDao =
        filmsDataBase.filmsDao()
}