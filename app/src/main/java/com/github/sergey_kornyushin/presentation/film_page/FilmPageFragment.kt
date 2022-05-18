package com.github.sergey_kornyushin.presentation.film_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.databinding.FragmentFilmPageBinding
import com.github.sergey_kornyushin.presentation.films_list.FilmsListPresenter
import com.github.sergey_kornyushin.presentation.films_list.FilmsListView
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class FilmPageFragment : MvpAppCompatFragment(), FilmsListView {
    private lateinit var binding: FragmentFilmPageBinding

    @Inject
    lateinit var presenterProvider: Provider<FilmsListPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load("https://st.kp.yandex.net/images/film_iphone/iphone360_326.jpg").into(binding.imgFilmPoster)
    }

    override fun showError(message: String) {

    }

    override fun showSortedFilms() {

    }

    override fun showLoading(isLoading: Boolean) {

    }

    override fun fillRVList(filmsList: List<RVFilmItem>) {

    }
}