package com.github.sergey_kornyushin.presentation.films_list.recycler_view

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.core.view.marginLeft
import androidx.recyclerview.widget.RecyclerView
import com.github.sergey_kornyushin.R


class RVFilmItemDecorator : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)

        if (view.left == 0) {
            outRect.left = 25
        }
        if (view.right == 0) {
            outRect.right = 25
        }
    }
}