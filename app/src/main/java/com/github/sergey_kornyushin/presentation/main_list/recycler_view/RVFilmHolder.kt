package com.github.sergey_kornyushin.presentation.main_list.recycler_view

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.github.sergey_kornyushin.databinding.RvItemFilmBinding
import com.github.sergey_kornyushin.databinding.RvItemGenreBinding
import com.github.sergey_kornyushin.databinding.RvItemTitleBinding
import com.squareup.picasso.Picasso

sealed class RVFilmHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root){

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
        fun bind(genre: RVFilmItem.Genre) {
            binding.tvRvTitle.text = genre.name
        }
    }

    data class FilmViewHolder(
        private val binding: RvItemFilmBinding
    ) : RVFilmHolder(binding) {
        fun bind(filmItem: RVFilmItem.FilmItem) {
            binding.tvFilmName.text = filmItem.name
            if (filmItem.image_url.isNotEmpty()) {
                Picasso.get().load(filmItem.image_url).into(binding.imgPoster)
            } else {
                binding.imgPoster.setImageResource(0)
            }
        }
    }
}