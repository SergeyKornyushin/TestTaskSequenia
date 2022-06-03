package com.github.sergey_kornyushin.presentation.films_list.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.databinding.RvItemFilmBinding
import com.github.sergey_kornyushin.databinding.RvItemGenreBinding
import com.github.sergey_kornyushin.databinding.RvItemTitleBinding
import com.github.sergey_kornyushin.presentation.extentions.filmsStartsFromEven
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.interfaces.RVClickListener
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.interfaces.RVGenreClick
import javax.inject.Inject

class RVFilmsAdapter @Inject constructor(private var selectedItemPosition: SelectedPositionSaver) :
    RecyclerView.Adapter<RVFilmHolder>(), RVGenreClick {

    private lateinit var clickListener: RVClickListener
    private var filmsStartsFromEven: Boolean = false

    private val differ = AsyncListDiffer(this, DiffCallback())

    var list: MutableList<RVFilmItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
            filmsStartsFromEven = value.filmsStartsFromEven()
        }

    fun setClickListener(clickListener: RVClickListener) {
        this.clickListener = clickListener
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
            else -> throw IllegalArgumentException(parent.context.getString(R.string.invalid_type_provided))
        }
    }

    override fun onBindViewHolder(holder: RVFilmHolder, position: Int) {
        when (holder) {
            is RVFilmHolder.TitleViewHolder -> holder.bind(
                list[position] as RVFilmItem.Title
            )
            is RVFilmHolder.GenreViewHolder -> {
                holder.bind(
                    list[position] as RVFilmItem.Genre,
                    clickListener,
                    position == selectedItemPosition.position,
                    this
                )
            }
            is RVFilmHolder.FilmViewHolder -> {
                holder.bind(
                    list[position] as RVFilmItem.FilmItem,
                    clickListener,
                    filmsStartsFromEven
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is RVFilmItem.Title -> R.layout.rv_item_title
            is RVFilmItem.Genre -> R.layout.rv_item_genre
            is RVFilmItem.FilmItem -> R.layout.rv_item_film
        }
    }

    override fun getItemCount() = list.size

    override fun setSingleSelection(adapterPosition: Int) {
        if (adapterPosition == RecyclerView.NO_POSITION) return


        notifyItemChanged(selectedItemPosition.position)

        if (selectedItemPosition.position == adapterPosition) {
            selectedItemPosition.position = DEFAULT_POSITION
        } else {
            selectedItemPosition.position = adapterPosition
            notifyItemChanged(adapterPosition)
        }
    }

    private companion object {
        private const val DEFAULT_POSITION = -1
    }
}