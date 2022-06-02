package com.github.sergey_kornyushin.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RealmModule {
    private const val realmVersion = 1L

    @Singleton
    @Provides
    fun providesRealmConfig(): RealmConfiguration =
        RealmConfiguration.Builder()
            .schemaVersion(realmVersion)
            .build()

    @Singleton
    @Provides
    fun provideRealmInstance(config: RealmConfiguration) =
        Realm.getInstance(config)
}