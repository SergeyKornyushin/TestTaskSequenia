package com.github.sergey_kornyushin.data.repository

import com.github.sergey_kornyushin.common.Resource
import com.github.sergey_kornyushin.data.database.dao.FilmsDao
import com.github.sergey_kornyushin.data.repository.mappers.DomainSingleFilmMapper
import com.github.sergey_kornyushin.domain.model.Film
import com.github.sergey_kornyushin.domain.repository.SelectedFilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilmPageRepositoryImpl @Inject constructor(
    private val filmsDao: FilmsDao,
    private val singleFilmMapper: DomainSingleFilmMapper
) : SelectedFilmRepository {

    override fun getSelectedFilm(film: Film): Flow<Resource<Film>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(singleFilmMapper.mapFilmToDomain(filmsDao.getFilmById(film.filmId))))
    }
}