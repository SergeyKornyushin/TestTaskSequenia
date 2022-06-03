package com.github.sergey_kornyushin.presentation.films_list.recycler_view

import android.util.Log
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.databinding.RvItemFilmBinding
import com.github.sergey_kornyushin.databinding.RvItemGenreBinding
import com.github.sergey_kornyushin.databinding.RvItemTitleBinding
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.interfaces.RVClickListener
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.interfaces.RVGenreClick
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

sealed class RVFilmHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    data class TitleViewHolder(
        private val binding: RvItemTitleBinding
    ) : RVFilmHolder(binding) {
        fun bind(title: RVFilmItem.Title) {
            binding.tvRvTitle.text = title.title
        }
    }

    data class GenreViewHolder(
        private val binding: RvItemGenreBinding
    ) : RVFilmHolder(binding) {
        fun bind(
            genre: RVFilmItem.Genre,
            clickListener: RVClickListener,
            isSelected: Boolean,
            adapterClick: RVGenreClick
        ) {
            with(binding) {
                if (isSelected) {
                    itemView.setBackgroundColor(
                        ContextCompat.getColor(genreItem.context, R.color.item_selected_color)
                    )
                } else {
                    itemView.setBackgroundColor(
                        ContextCompat.getColor(genreItem.context, R.color.background_color)
                    )
                }
                tvRvTitle.text = genre.name
                root.setOnClickListener {
                    clickListener.genreClick(genre)
                    adapterClick.setSingleSelection(adapterPosition)
                }
            }
        }
    }

    data class FilmViewHolder(
        private val binding: RvItemFilmBinding
    ) : RVFilmHolder(binding) {
        fun bind(
            filmItem: RVFilmItem.FilmItem,
            clickListener: RVClickListener,
            startsFromEven: Boolean
        ) {
            binding.apply {
                root.setOnClickListener {
                    clickListener.filmClick(
                        filmItem.id,
                        filmItem.localized_name
                    )
                }
                tvFilmName.text = filmItem.localized_name
                if (filmItem.image_url.isNotEmpty()) {
                    Picasso.get().load(filmItem.image_url)
                        .into(binding.imgPoster, object : Callback {
                            override fun onSuccess() {
                                tvNotFound.isVisible = false
                            }

                            override fun onError(e: Exception?) {
                                tvNotFound.isVisible = true
                            }
                        })
                } else {
                    binding.imgPoster.setImageResource(0)
                    tvNotFound.isVisible = true
                }
            }
            val layoutParams = (itemView.layoutParams as ViewGroup.MarginLayoutParams)
            if (startsFromEven) {
                if (adapterPosition % 2 == 0) {
                    layoutParams.setMargins(32, 16, 16, 0)
                    itemView.layoutParams = layoutParams
                } else {
                    layoutParams.setMargins(16, 16, 32, 0)
                    itemView.layoutParams = layoutParams
                }
            } else {
                if (adapterPosition % 2 == 0) {
                    layoutParams.setMargins(16, 16, 32, 0)
                    itemView.layoutParams = layoutParams
                } else {
                    layoutParams.setMargins(32, 16, 16, 0)
                    itemView.layoutParams = layoutParams
                }

            }
        }
    }
}