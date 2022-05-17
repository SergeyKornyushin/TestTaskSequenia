package com.github.sergey_kornyushin.data.repository
//
//import com.github.sergey_kornyushin.common.Resource
//import com.github.sergey_kornyushin.data.remote.FilmsApi
//import com.github.sergey_kornyushin.data.remote.dto.FilmDto
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import retrofit2.HttpException
//import java.io.IOException
//import javax.inject.Inject
//
//interface ApiRepository {
//
//    fun getFilms(): Flow<Resource<List<FilmDto>>>
//
//    class Base constructor(
//        private val api: FilmsApi
//    ) : ApiRepository {
//
//        override fun getFilms(): Flow<Resource<List<FilmDto>>> = flow {
//            try {
//                emit(Resource.Loading())
//                val films = api.getFilms().filmsDto
//                emit(Resource.Success(films))
//            } catch (e: HttpException) {
//                emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
//            } catch (e: IOException) {
//                emit(Resource.Error("Couldn't reach server. Check your internet connection"))
//            }
//        }
//    }
//}