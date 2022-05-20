package com.github.sergey_kornyushin.presentation.film_page

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.databinding.FragmentFilmPageBinding
import com.github.sergey_kornyushin.domain.model.Film
import com.github.sergey_kornyushin.presentation.extentions.snackbar
import com.github.sergey_kornyushin.presentation.ui.BaseFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class FilmPageFragment : BaseFragment<FragmentFilmPageBinding>(FragmentFilmPageBinding::inflate),
    FilmPageView {
    private val args: FilmPageFragmentArgs by navArgs()

    @Inject
    lateinit var presenterProvider: Provider<FilmPagePresenter.Base>
    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getFilm(args.filmId)
    }

    override fun showLoading(isLoading: Boolean) {
        binding.pbDataDownload.isVisible = isLoading
    }

    override fun showError(message: String) {
        binding.root.snackbar(message)
    }

    override fun showFilm(film: Film) {
        binding.apply {
            pbImageDownload.isVisible = true
            tvFilmTitle.text = film.name
            tvFilmYear.text = context?.getString(R.string.film_page_year, film.year)
            tvFilmRating.text = context?.getString(R.string.film_page_rating, film.rating)
            tvFilmDescription.text = film.description
            if (film.image_url.isNotEmpty()) {
                Picasso.get().load(film.image_url).into(imgFilmPoster, object : Callback {
                    override fun onSuccess() {
                        tvImageNotFount.isVisible = false
                        pbImageDownload.isVisible = false
                    }

                    override fun onError(e: Exception?) {
                        tvImageNotFount.isVisible = true
                        pbImageDownload.isVisible = false
                    }
                })
            } else {
                tvImageNotFount.isVisible = true
                pbImageDownload.isVisible = false
            }
        }
    }
}