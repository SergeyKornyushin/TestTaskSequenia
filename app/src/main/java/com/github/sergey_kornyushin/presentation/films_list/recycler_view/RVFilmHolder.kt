package com.github.sergey_kornyushin.presentation.films_list.recycler_view

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.databinding.RvItemFilmBinding
import com.github.sergey_kornyushin.databinding.RvItemGenreBinding
import com.github.sergey_kornyushin.databinding.RvItemTitleBinding
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.interfaces.RVClickListener
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.interfaces.RVGenreClick
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
                    genreItem.background =
                        ContextCompat.getDrawable(genreItem.context, R.drawable.light_background)
                } else {
                    genreItem.background =
                        ContextCompat.getDrawable(genreItem.context, R.drawable.dark_background)
                }
                tvRvTitle.text = genre.name
                root.setOnClickListener {
                    clickListener.genreClick(genre, position)
                    adapterClick.setSingleSelection(adapterPosition)
                }

            }

        }
    }

    data class FilmViewHolder(
        private val binding: RvItemFilmBinding
    ) : RVFilmHolder(binding) {
        fun bind(filmItem: RVFilmItem.FilmItem, clickListener: RVClickListener) {
            binding.apply {
                root.setOnClickListener {
                    clickListener.filmClick(
                        filmItem.id,
                        filmItem.localized_name
                    )
                }
                tvFilmName.text = filmItem.localized_name
                if (filmItem.image_url.isNotEmpty()) {
                    Picasso.get().load(filmItem.image_url).into(binding.imgPoster)
                } else {
                    binding.imgPoster.setImageResource(0)
                }
            }
        }
    }
}