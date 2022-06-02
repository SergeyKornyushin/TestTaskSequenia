package com.github.sergey_kornyushin.data.repository

import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.common.ResourceProvider
import com.github.sergey_kornyushin.data.database.realm.RealmOperations
import com.github.sergey_kornyushin.data.database.realm.mappers.RealmMappersSet
import com.github.sergey_kornyushin.data.remote.FilmsApi
import com.github.sergey_kornyushin.data.repository.mappers.DomainListFiller
import com.github.sergey_kornyushin.domain.model.Genre
import com.github.sergey_kornyushin.domain.repository.FilmsRepository
import com.github.sergey_kornyushin.domain.repository.SortRepository
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface FilmsRepositoryImpl{
    class Base @Inject constructor(
        private val realmOperations: RealmOperations,
        private val api: FilmsApi,
        private val mappers: RealmMappersSet,
        private val listFiller: DomainListFiller,
        private val resourceProvider: ResourceProvider
    ) : FilmsRepositoryImpl, FilmsRepository, SortRepository {

        override fun getFilms(): Flow<Resource<List<RVFilmItem>>> = flow {
            try {
                emit(Resource.Loading())
                mappers.saveFilms(api.getFilms(), realmOperations)
                emit(
                    Resource.Success(
                        listFiller.createListForRecyclerView(
                            realmOperations.getAllGenres(),
                            realmOperations.getAllFilms()
                        )
                    )
                )
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        e.localizedMessage ?: resourceProvider.getString(R.string.unexpected_error)
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error(resourceProvider.getString(R.string.check_internet_error)))
            }
        }.flowOn(Dispatchers.IO)

        override fun sortFilmsByGenre(genre: Genre): Flow<Resource<List<RVFilmItem>>> = flow {
            try {
                emit(Resource.Loading())
                emit(
                    Resource.Success(
                        listFiller.createListForRecyclerView(
                            realmOperations.getAllGenres(),
                            realmOperations.getGenreWithFilms()
                        )
                    )
                )
            } catch (e: NullPointerException) {
                emit(Resource.Error(resourceProvider.getString(R.string.unexpected_error)))
            }
        }.flowOn(Dispatchers.IO)
    }
}