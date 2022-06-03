package com.github.sergey_kornyushin._new_structure.kotlinapp.ui.list.view_holders

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * Базовый ViewHolder
 */
open class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    constructor(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        @LayoutRes contentLayoutId: Int
    ) : this(layoutInflater.inflate(contentLayoutId, parent, false))

    /**
     * Отключить свойство @StaggeredGridLayoutManager
     */
    protected fun disableStaggeredGridAbility() {
        val layoutParams = itemView.layoutParams
        if (layoutParams is StaggeredGridLayoutManager.LayoutParams) {
            layoutParams.isFullSpan = true
        }
    }
}