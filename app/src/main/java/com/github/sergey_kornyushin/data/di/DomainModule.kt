package com.github.sergey_kornyushin.data.di

import com.github.sergey_kornyushin.data.database.dao.FilmsDao
import com.github.sergey_kornyushin.data.database.mappers.FilmsGenresCrossRefMapper
import com.github.sergey_kornyushin.data.database.mappers.FilmsToDbMapper
import com.github.sergey_kornyushin.data.database.mappers.GenresToDbMapper
import com.github.sergey_kornyushin.data.database.mappers.MappersSet
import com.github.sergey_kornyushin.data.remote.FilmsApi
import com.github.sergey_kornyushin.data.repository.FilmsRepositoryImpl
import com.github.sergey_kornyushin.data.repository.mappers.DomainListFiller
import com.github.sergey_kornyushin.data.repository.mappers.DomainRecyclerViewMapper
import com.github.sergey_kornyushin.domain.repository.FilmsRepository
import com.github.sergey_kornyushin.domain.repository.SortRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideFilmsRepository(
        filmsDao: FilmsDao,
        filmsApi: FilmsApi,
        mappersSet: MappersSet.Base,
        listFiller: DomainListFiller
    ): FilmsRepository {
        return FilmsRepositoryImpl(filmsDao, filmsApi, mappersSet, listFiller)
    }

    @Singleton
    @Provides
    fun provideSortRepository(
        filmsDao: FilmsDao,
        filmsApi: FilmsApi,
        mappersSet: MappersSet.Base,
        listFiller: DomainListFiller
    ): SortRepository {
        return FilmsRepositoryImpl(filmsDao, filmsApi, mappersSet, listFiller)
    }

    @Singleton
    @Provides
    fun provideMappersSet(): MappersSet {
        return MappersSet.Base(
            FilmsToDbMapper(),
            GenresToDbMapper(),
            FilmsGenresCrossRefMapper()
        )
    }

    @Singleton
    @Provides
    fun provideDomainListFiller(): DomainListFiller{
        return DomainListFiller.Base(DomainRecyclerViewMapper.Base())
    }
}