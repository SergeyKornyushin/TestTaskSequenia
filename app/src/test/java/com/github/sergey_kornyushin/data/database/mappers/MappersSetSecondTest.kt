package com.github.sergey_kornyushin.data.database.mappers

import com.github.sergey_kornyushin.data.database.dao.FilmsDao
import com.github.sergey_kornyushin.data.database.model.FilmEntity
import com.github.sergey_kornyushin.data.database.model.GenreEntity
import com.github.sergey_kornyushin.data.database.relations.FilmsGenresCrossRef
import com.github.sergey_kornyushin.data.remote.dto.FilmDto
import com.github.sergey_kornyushin.data.remote.dto.FilmsDto
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class MappersSetSecondTest {

    private val fakeFilmsDao = mock(FilmsDao::class.java)
    private val filmsDto = FilmsDto(
        mutableListOf(
            FilmDto(
                0,
                mutableListOf("драма"),
                "",
                "loc_name",
                "name",
                1990,
                10.0,
                "desc"
            )
        )
    )

    inner class FakeMappersSet : MappersSet {
        override suspend fun saveFilms(networkFilms: FilmsDto, filmsDao: FilmsDao) {
            filmsDao.clearAllTables()

            filmsDao.insertFilm(film)
            filmsDao.insertGenre(genre)
            filmsDao.insertFilmsGenreCrossRef(filmsGenresCrossRef)
        }
    }

    private lateinit var film: FilmEntity
    private lateinit var genre: GenreEntity
    private lateinit var filmsGenresCrossRef: FilmsGenresCrossRef

    private val fakeFilmTable = mutableListOf<FilmEntity>()
    private val fakeGenresTable = mutableListOf<GenreEntity>()
    private val fakeFilmsGenresCrossRefTable = mutableListOf<FilmsGenresCrossRef>()

    @Before
    fun setup() {
        fakeFilmTable.clear()
        fakeGenresTable.clear()
        fakeFilmsGenresCrossRefTable.clear()

        film = FilmEntity(
            0, "", "loc_name", "name",
            "1990", "10.0", "desc"
        )
        genre = GenreEntity("драма")
        filmsGenresCrossRef = FilmsGenresCrossRef(0, "драма")
    }

    @Test
    fun `save entities to db must be success`() {
        runBlocking {
            `when`(fakeFilmsDao.clearAllTables()).then {
                fakeFilmTable.clear()
                fakeGenresTable.clear()
                fakeFilmsGenresCrossRefTable.clear()
            }

            `when`(fakeFilmsDao.insertFilm(film)).then { fakeFilmTable.add(film) }
            `when`(fakeFilmsDao.insertGenre(genre)).then { fakeGenresTable.add(genre) }
            `when`(fakeFilmsDao.insertFilmsGenreCrossRef(filmsGenresCrossRef)).then {
                fakeFilmsGenresCrossRefTable.add(
                    filmsGenresCrossRef
                )
            }

            val fakeMapperSet = FakeMappersSet()
            fakeMapperSet.saveFilms(filmsDto, fakeFilmsDao)

            assertThat(fakeFilmTable).contains(film)
            assertThat(fakeGenresTable).contains(genre)
            assertThat(fakeFilmsGenresCrossRefTable).contains(filmsGenresCrossRef)

            verify(fakeFilmsDao, times(1)).clearAllTables()
            verify(fakeFilmsDao, times(1)).insertFilm(film)
            verify(fakeFilmsDao, times(1)).insertGenre(genre)
            verify(fakeFilmsDao, times(1)).insertFilmsGenreCrossRef(filmsGenresCrossRef)
        }
    }
}