package com.github.sergey_kornyushin.data.database.mappers

import javax.inject.Inject

data class MappersSet @Inject constructor(
    val filmsToDbMapper: FilmsToDbMapper,
    val genresToDbMapper: GenresToDbMapper,
    val filmsGenresCrossRefMapper: FilmsGenresCrossRefMapper
)