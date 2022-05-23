package com.github.sergey_kornyushin.data.database.mappers

import com.github.sergey_kornyushin.data.database.relations.FilmsGenresCrossRef
import com.github.sergey_kornyushin.data.remote.dto.FilmDto
import com.github.sergey_kornyushin.data.remote.dto.FilmsDto
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class FilmsGenresCrossRefMapperTest {

    private val mapper = FilmsGenresCrossRefMapper()
    private val films = mutableListOf<FilmDto>()
    private val genresFirstList = mutableListOf("детектив", "драма", "комедия")
    private val genresSecondList = mutableListOf("детектив", "комедия", "триллер")
    private val blankList = mutableListOf(" ", " ")

    @Before
    fun setup() {
        films.add(FilmDto(0, null))
        films.add(FilmDto(1, genresFirstList))
        films.add(FilmDto(2, genresFirstList))
        films.add(FilmDto(3, genresSecondList))
        films.add(FilmDto(4))
        films.add(FilmDto(5, blankList))
    }

    @Test
    fun `mapping completed correctly`() {
        val result = mapper.map(FilmsDto(films))
        val expect = mutableListOf<FilmsGenresCrossRef>()
        expect.add(FilmsGenresCrossRef(1, "детектив"))
        expect.add(FilmsGenresCrossRef(1, "драма"))
        expect.add(FilmsGenresCrossRef(1, "комедия"))
        expect.add(FilmsGenresCrossRef(2, "детектив"))
        expect.add(FilmsGenresCrossRef(2, "драма"))
        expect.add(FilmsGenresCrossRef(2, "комедия"))
        expect.add(FilmsGenresCrossRef(3, "детектив"))
        expect.add(FilmsGenresCrossRef(3, "комедия"))
        expect.add(FilmsGenresCrossRef(3, "триллер"))

        assertThat(result).isEqualTo(expect)
    }
}