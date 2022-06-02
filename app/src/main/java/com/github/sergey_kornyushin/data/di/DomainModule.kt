package com.github.sergey_kornyushin.data.di

import com.github.sergey_kornyushin.common.ResourceProvider
import com.github.sergey_kornyushin.data.database.dao.FilmsDao
import com.github.sergey_kornyushin.data.database.realm.RealmOperations
import com.github.sergey_kornyushin.data.database.realm.mappers.RealmFilmsToDbMapper
import com.github.sergey_kornyushin.data.database.realm.mappers.RealmGenresToDbMapper
import com.github.sergey_kornyushin.data.database.realm.mappers.RealmMappersSet
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
        realmOperations: RealmOperations,
        filmsApi: FilmsApi,
        RealmMappersSet: RealmMappersSet.Base,
        listFiller: DomainListFiller,
        resourceProvider: ResourceProvider
    ): FilmsRepository {
        return FilmsRepositoryImpl.Base(
            realmOperations,
            filmsApi,
            RealmMappersSet,
            listFiller,
            resourceProvider
        )
    }

    @Provides
    fun provideSortRepository(
        realmOperations: RealmOperations,
        filmsApi: FilmsApi,
        realmMappersSet: RealmMappersSet.Base,
        listFiller: DomainListFiller,
        resourceProvider: ResourceProvider
    ): SortRepository {
        return FilmsRepositoryImpl.Base(
            realmOperations,
            filmsApi,
            realmMappersSet,
            listFiller,
            resourceProvider
        )
    }

    @Provides
    fun provideSelectedFilmRepository(
        realmOperations: RealmOperations,
        domainSingleFilmMapper: DomainSingleFilmMapper.Base,
        resourceProvider: ResourceProvider
    ): SelectedFilmRepository {
        return FilmPageRepositoryImpl.Base(realmOperations, domainSingleFilmMapper, resourceProvider)
    }

    @Provides
    fun provideRealmMappersSet(resourceProvider: ResourceProvider): RealmMappersSet {
        return RealmMappersSet.Base(
            RealmFilmsToDbMapper(resourceProvider),
            RealmGenresToDbMapper()
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