package com.github.sergey_kornyushin.data.database.mappers

interface ResponseMapper<FILM, T> {
    fun map(films: FILM): T
}