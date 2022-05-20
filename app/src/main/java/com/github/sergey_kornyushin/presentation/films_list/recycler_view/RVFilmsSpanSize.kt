package com.github.sergey_kornyushin.presentation.films_list.recycler_view

import androidx.recyclerview.widget.GridLayoutManager

class RVFilmsSpanSize(private val rvAdapter: RVFilmsAdapter) : GridLayoutManager.SpanSizeLookup() {

    override fun getSpanSize(position: Int): Int {
        val item = rvAdapter.list.getOrNull(position) ?: return SPAN_COLUMN
        return when(item){
            is RVFilmItem.Title -> SINGLE_COLUMN
            is RVFilmItem.Genre -> SINGLE_COLUMN
            is RVFilmItem.FilmItem -> DOUBLE_COLUMN
        }
    }

    private companion object{
        private const val SPAN_COLUMN = 2
        private const val SINGLE_COLUMN = 2
        private const val DOUBLE_COLUMN = 1
    }
}