package com.github.sergey_kornyushin.data.repository

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.data.database.dao.FilmsDao
import com.github.sergey_kornyushin.data.database.mappers.MappersSet
import com.github.sergey_kornyushin.data.remote.FilmsApi
import com.github.sergey_kornyushin.data.repository.mappers.DomainListFiller
import com.github.sergey_kornyushin.domain.repository.FilmsRepository
import com.github.sergey_kornyushin.presentation.main_list.recycler_view.RecyclerViewItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val filmsDao: FilmsDao,
    private val api: FilmsApi,
    private val mappers: MappersSet.Base,
    private val listFiller: DomainListFiller
) : FilmsRepository {

    override suspend fun getAndSaveFilms(): Flow<Resource<List<RecyclerViewItem>>> = flow {
        emit(Resource.Loading())
        try {
            val networkFilms = api.getFilms()
            mappers.saveFilms(networkFilms, filmsDao)
//            val filmsEntity = mappers.filmsToDbMapper.map(networkFilms)
//            filmsEntity.forEach { filmsDao.insertFilm(it) }
//            val genresEntity = mappers.genresToDbMapper.map(networkFilms)
//            genresEntity.forEach { filmsDao.insertGenre(it) }
//            val filmsGenresEntity = mappers.filmsGenresCrossRefMapper.map(networkFilms)
//            filmsGenresEntity.forEach { filmsDao.insertFilmsGenreCrossRef(it) }
            emit(Resource.Success(listFiller.createListForRecyclerView(filmsDao)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}