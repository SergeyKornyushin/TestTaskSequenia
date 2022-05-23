package com.github.sergey_kornyushin.data.database.mappers

import com.github.sergey_kornyushin.data.database.model.GenreEntity
import com.github.sergey_kornyushin.data.remote.dto.FilmDto
import com.github.sergey_kornyushin.data.remote.dto.FilmsDto
import org.junit.Before
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GenresToDbMapperTest {

    private val mapper = GenresToDbMapper()

    private val films = mutableListOf<FilmDto>()
    private val genresFirstList = mutableListOf("детектив", "драма", "комедия")
    private val genresSecondList = mutableListOf("детектив", "комедия", "триллер")
    private val blankList = mutableListOf(" ", " ")

    @Before
    fun setup() {
        films.clear()
        films.add(FilmDto(0, null))
        films.add(FilmDto(1, genresFirstList))
        films.add(FilmDto(2, genresFirstList))
        films.add(FilmDto(3, genresSecondList))
        films.add(FilmDto(4))
        films.add(FilmDto(5, blankList))
    }

    @Test
    fun `map FilmsDto all genres cases`() {
        val filmsDto = FilmsDto(films)

        val result = mapper.map(filmsDto)
        val expect = mutableListOf(
            GenreEntity("детектив"),
            GenreEntity("драма"),
            GenreEntity("комедия"),
            GenreEntity("триллер")
        )

        assertThat(result).containsExactlyElementsIn(expect)
    }
}