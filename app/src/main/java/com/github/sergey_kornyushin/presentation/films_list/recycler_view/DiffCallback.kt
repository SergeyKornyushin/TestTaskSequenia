package com.github.sergey_kornyushin.presentation.films_list.recycler_view

import androidx.recyclerview.widget.DiffUtil

class DiffCallback : DiffUtil.ItemCallback<RVFilmItem>() {
    override fun areItemsTheSame(oldItem: RVFilmItem, newItem: RVFilmItem): Boolean {
        val isSameGenre = oldItem is RVFilmItem.Genre
                && newItem is RVFilmItem.Genre
                && oldItem.name == newItem.name
                && oldItem.isChecked == newItem.isChecked

        val isSameFilm = oldItem is RVFilmItem.FilmItem
                && newItem is RVFilmItem.FilmItem
                && oldItem.id == newItem.id

        val isSameTitle = oldItem is RVFilmItem.Title
                && newItem is RVFilmItem.Title
                && oldItem.id == newItem.id
        return isSameGenre || isSameFilm || isSameTitle
    }

    override fun areContentsTheSame(oldItem: RVFilmItem, newItem: RVFilmItem) =
        oldItem == newItem
}