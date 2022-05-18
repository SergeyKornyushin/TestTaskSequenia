package com.github.sergey_kornyushin.presentation.films_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.databinding.FragmentFilmsListBinding
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVClickListener
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmsAdapter
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmsSpanSize
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class FilmsListFragment : MvpAppCompatFragment(), FilmsListView, RVClickListener {
    private lateinit var binding: FragmentFilmsListBinding
    private val rvFilmsAdapter = RVFilmsAdapter(this)

    @Inject
    lateinit var presenterProvider: Provider<FilmsListPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridLayoutManager = GridLayoutManager(context, 2)

        gridLayoutManager.spanSizeLookup = RVFilmsSpanSize(rvFilmsAdapter)

        binding.rvFilmsList.apply {
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = rvFilmsAdapter
        }

//        presenter.getFilmsList()
    }

    override fun showError(message: String) {

    }

    override fun showSortedFilms() {
        TODO("Not yet implemented")
    }

    override fun showLoading(isLoading: Boolean) {
        binding.pbDownload.isVisible = isLoading
    }

    override fun fillRVList(filmsList: List<RVFilmItem>) {
        rvFilmsAdapter.list = filmsList
    }

    override fun genreClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun filmClick(filmId: Int, filmName: String) {
        findNavController().navigate(
            FilmsListFragmentDirections.actionFilmsListFragmentToFilmPageFragment(
                filmId,
                filmName
            )
        )
    }
}