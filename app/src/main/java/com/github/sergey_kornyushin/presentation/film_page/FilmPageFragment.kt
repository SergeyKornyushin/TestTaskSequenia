package com.github.sergey_kornyushin.presentation.film_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.databinding.FragmentFilmPageBinding
import com.github.sergey_kornyushin.domain.model.Film
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class FilmPageFragment : MvpAppCompatFragment(), FilmPageView {
    private lateinit var binding: FragmentFilmPageBinding
    private val args: FilmPageFragmentArgs by navArgs()

    @Inject
    lateinit var presenterProvider: Provider<FilmPagePresenter.Base>

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
        presenter.getFilm(args.filmId)
    }

    override fun showError(message: String) {

    }

    override fun showFilm(film: Film) {
        binding.apply {
            pbImageDownloading.isVisible = true
            tvFilmTitle.text = film.name
            tvFilmYear.text = context?.getString(R.string.film_page_year, film.year)
            tvFilmRating.text = context?.getString(R.string.film_page_rating, film.rating)
            tvFilmDescription.text = film.description
            if (film.image_url.isNotEmpty()) {
                Picasso.get().load(film.image_url).into(imgFilmPoster, object : Callback {
                    override fun onSuccess() {
                        tvImageNotFount.isVisible = false
                        pbImageDownloading.isVisible = false
                    }

                    override fun onError(e: Exception?) {
                        tvImageNotFount.isVisible = true
                        pbImageDownloading.isVisible = false
                    }
                })
            } else {
                tvImageNotFount.isVisible = true
                pbImageDownloading.isVisible = false
            }
        }
    }
}