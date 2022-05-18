package com.github.sergey_kornyushin.presentation.films_list.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.databinding.RvItemFilmBinding
import com.github.sergey_kornyushin.databinding.RvItemGenreBinding
import com.github.sergey_kornyushin.databinding.RvItemTitleBinding

class RVFilmsAdapter(private val clickListener: RVClickListener) :
    RecyclerView.Adapter<RVFilmHolder>() {

    private val differ = AsyncListDiffer(this, DiffCallback())

    var list: List<RVFilmItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVFilmHolder {
        return when (viewType) {
            R.layout.rv_item_title -> RVFilmHolder.TitleViewHolder(
                RvItemTitleBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            R.layout.rv_item_genre -> RVFilmHolder.GenreViewHolder(
                RvItemGenreBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            R.layout.rv_item_film -> RVFilmHolder.FilmViewHolder(
                RvItemFilmBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: RVFilmHolder, position: Int) {
        when (holder) {
            is RVFilmHolder.TitleViewHolder -> holder.bind(
                list[position] as RVFilmItem.Title
            )
            is RVFilmHolder.GenreViewHolder -> holder.bind(
                list[position] as RVFilmItem.Genre,
                clickListener
            )
            is RVFilmHolder.FilmViewHolder -> holder.bind(
                list[position] as RVFilmItem.FilmItem,
                clickListener
            )
        }
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is RVFilmItem.Title -> R.layout.rv_item_title
            is RVFilmItem.Genre -> R.layout.rv_item_genre
            is RVFilmItem.FilmItem -> R.layout.rv_item_film
        }
    }
}