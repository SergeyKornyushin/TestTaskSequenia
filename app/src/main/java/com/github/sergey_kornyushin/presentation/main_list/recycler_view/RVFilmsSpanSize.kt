package com.github.sergey_kornyushin.presentation.main_list.recycler_view

import androidx.recyclerview.widget.GridLayoutManager

class RVFilmsSpanSize(private val rvAdapter: RVFilmsAdapter) : GridLayoutManager.SpanSizeLookup() {

    override fun getSpanSize(position: Int): Int {
        val item = rvAdapter.list.getOrNull(position) ?: return SPAN_COUNT
        return when(item){
            is RVFilmItem.Title -> SINGLE_LINE
            is RVFilmItem.Genre -> SINGLE_LINE
            is RVFilmItem.FilmItem -> DOUBLE_LINE
        }
    }

    companion object{
        private const val SPAN_COUNT = 2
        private const val SINGLE_LINE = 2
        private const val DOUBLE_LINE = 1
    }
}