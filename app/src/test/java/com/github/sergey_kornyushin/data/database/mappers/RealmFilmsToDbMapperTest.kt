package com.github.sergey_kornyushin.data.database.mappers

import com.github.sergey_kornyushin.common.ResourceProvider
import com.github.sergey_kornyushin.data.database.model.FilmEntity
import com.github.sergey_kornyushin.data.remote.dto.FilmDto
import com.github.sergey_kornyushin.data.remote.dto.FilmsDto
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class RealmFilmsToDbMapperTest {

    private val testResourceProvider = mock(ResourceProvider::class.java)
    private val mapper = FilmsToDbMapper(testResourceProvider)
    private val films = mutableListOf<FilmDto>()

    @Before
    fun setup() {
        films.clear()
        `when`(testResourceProvider.getString(anyInt())).thenReturn("Неизвестно")
    }

    @Test
    fun `pass filmsDto with nulls`() {
        films.add(
            FilmDto(
                null, null, null, null,
                null, null, null, null
            )
        )
        val filmsDto = FilmsDto(films)

        val result = mapper.map(filmsDto)
        val expect = mutableListOf<FilmEntity>()
        expect.add(
            FilmEntity(
                0,
                "",
                "Неизвестно",
                "Неизвестно",
                "Неизвестно",
                "Неизвестно",
                "Неизвестно"
            )
        )
        assertThat(result).isEqualTo(expect)
    }

    @Test
    fun `pass filmsDto with empty fields`() {
        val genresBlankList = mutableListOf(" ", " ")
        films.add(
            FilmDto(
                5, genresBlankList, "", "",
                "", 0, 0.0, ""
            )
        )
        val filmsDto = FilmsDto(films)

        val result = mapper.map(filmsDto)
        val expect = mutableListOf<FilmEntity>()
        expect.add(
            FilmEntity(
                5,
                "",
                "Неизвестно",
                "Неизвестно",
                "0",
                "0.0",
                "Неизвестно"
            )
        )
        assertThat(result).isEqualTo(expect)
    }

    @Test
    fun `pass filmsDto without few fields`() {
        films.add(FilmDto(id = 1, genres = mutableListOf(" ", " ")))
        val filmsDto = FilmsDto(films)

        val result = mapper.map(filmsDto)
        val expect = mutableListOf<FilmEntity>()
        expect.add(
            FilmEntity(
                1,
                "",
                "Неизвестно",
                "Неизвестно",
                "0",
                "Неизвестно",
                "Неизвестно"
            )
        )
        assertThat(result).isEqualTo(expect)
    }

    @Test
    fun `pass filmsDto with correct fields`() {
        val genresFirstList = mutableListOf("детектив", "драма", "комедия")
        films.add(
            FilmDto(
                0, genresFirstList, "image", "lic_name",
                "name", 2000, 10.0, "desc"
            )
        )
        val filmsDto = FilmsDto(films)

        val result = mapper.map(filmsDto)
        val expect = mutableListOf<FilmEntity>()
        expect.add(
            FilmEntity(
                0,
                "image",
                "lic_name",
                "name",
                "2000",
                "10.0",
                "desc"
            )
        )
        assertThat(result).isEqualTo(expect)
    }
}