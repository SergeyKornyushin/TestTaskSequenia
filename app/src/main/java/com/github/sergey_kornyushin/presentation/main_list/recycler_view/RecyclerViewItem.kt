package com.github.sergey_kornyushin.presentation.main_list.recycler_view

sealed class RecyclerViewItem{
    data class Title(
        val id: Int,
        val title: String
    ) : RecyclerViewItem()

    data class Genre(
        val name: String
    ) : RecyclerViewItem()

    data class FilmItem(
        val id: Int,
        val image_url: String,
        val localized_name: String,
    ) : RecyclerViewItem()
}
