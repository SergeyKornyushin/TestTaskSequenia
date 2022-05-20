package com.github.sergey_kornyushin.data.repository

import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.common.ResourceProvider
import com.github.sergey_kornyushin.data.database.dao.FilmsDao
import com.github.sergey_kornyushin.data.repository.mappers.DomainSingleFilmMapper
import com.github.sergey_kornyushin.domain.model.Film
import com.github.sergey_kornyushin.domain.repository.SelectedFilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.NullPointerException
import javax.inject.Inject

interface FilmPageRepositoryImpl {

    class Base @Inject constructor(
        private val filmsDao: FilmsDao,
        private val singleFilmMapper: DomainSingleFilmMapper,
        private val resourceProvider: ResourceProvider
    ) : FilmPageRepositoryImpl, SelectedFilmRepository {

        override fun getSelectedFilm(film: Film): Flow<Resource<Film>> = flow {
            try {
                emit(Resource.Loading())
                emit(Resource.Success(singleFilmMapper.mapFilmToDomain(filmsDao.getFilmById(film.filmId))))
            } catch (e: NullPointerException) {
                emit(Resource.Error(resourceProvider.getString(R.string.unexpected_error)))
            }
        }
    }
}