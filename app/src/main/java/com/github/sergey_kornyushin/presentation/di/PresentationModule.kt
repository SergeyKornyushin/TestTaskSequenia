package com.github.sergey_kornyushin.presentation.di

import android.content.Context
import androidx.room.Room
import com.github.sergey_kornyushin.data.database.FilmsDataBase
import com.github.sergey_kornyushin.data.database.dao.FilmsDao
import com.github.sergey_kornyushin.presentation.mappers.PresenterMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Singleton
    @Provides
    fun providePresenterMapper(): PresenterMapper =
        PresenterMapper.Base()
}