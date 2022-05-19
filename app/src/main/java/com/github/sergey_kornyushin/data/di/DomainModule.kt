package com.github.sergey_kornyushin.data.di

import com.github.sergey_kornyushin.common.ResourceProvider
import com.github.sergey_kornyushin.data.database.dao.FilmsDao
import com.github.sergey_kornyushin.data.database.mappers.FilmsGenresCrossRefMapper
import com.github.sergey_kornyushin.data.database.mappers.FilmsToDbMapper
import com.github.sergey_kornyushin.data.database.mappers.GenresToDbMapper
import com.github.sergey_kornyushin.data.database.mappers.MappersSet
import com.github.sergey_kornyushin.data.remote.FilmsApi
import com.github.sergey_kornyushin.data.repository.FilmPageRepositoryImpl
import com.github.sergey_kornyushin.data.repository.FilmsRepositoryImpl
import com.github.sergey_kornyushin.data.repository.mappers.DomainListFiller
import com.github.sergey_kornyushin.data.repository.mappers.DomainRecyclerViewMapper
import com.github.sergey_kornyushin.data.repository.mappers.DomainSingleFilmMapper
import com.github.sergey_kornyushin.domain.repository.FilmsRepository
import com.github.sergey_kornyushin.domain.repository.SelectedFilmRepository
import com.github.sergey_kornyushin.domain.repository.SortRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideFilmsRepository(
        filmsDao: FilmsDao,
        filmsApi: FilmsApi,
        mappersSet: MappersSet.Base,
        listFiller: DomainListFiller,
        resourceProvider: ResourceProvider
    ): FilmsRepository {
        return FilmsRepositoryImpl.Base(
            filmsDao,
            filmsApi,
            mappersSet,
            listFiller,
            resourceProvider
        )
    }

    @Provides
    fun provideSortRepository(
        filmsDao: FilmsDao,
        filmsApi: FilmsApi,
        mappersSet: MappersSet.Base,
        listFiller: DomainListFiller,
        resourceProvider: ResourceProvider
    ): SortRepository {
        return FilmsRepositoryImpl.Base(
            filmsDao,
            filmsApi,
            mappersSet,
            listFiller,
            resourceProvider
        )
    }

    @Provides
    fun provideSelectedFilmRepository(
        filmsDao: FilmsDao,
        domainSingleFilmMapper: DomainSingleFilmMapper.Base,
        resourceProvider: ResourceProvider
    ): SelectedFilmRepository {
        return FilmPageRepositoryImpl.Base(filmsDao, domainSingleFilmMapper, resourceProvider)
    }

    @Provides
    fun provideMappersSet(resourceProvider: ResourceProvider): MappersSet {
        return MappersSet.Base(
            FilmsToDbMapper(resourceProvider),
            GenresToDbMapper(),
            FilmsGenresCrossRefMapper()
        )
    }

    @Provides
    fun provideDomainListFiller(
        domainResourceMapper: DomainRecyclerViewMapper.Base,
        resourceProvider: ResourceProvider
    ): DomainListFiller {
        return DomainListFiller.Base(domainResourceMapper, resourceProvider)
    }
}