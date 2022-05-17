package com.github.sergey_kornyushin.data.di

import com.github.sergey_kornyushin.data.remote.FilmsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://s3-eu-west-1.amazonaws.com"

    @Singleton
    @Provides
    fun provideFilmsApi(): FilmsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FilmsApi::class.java)
    }
}