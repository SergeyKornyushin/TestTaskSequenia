package com.github.sergey_kornyushin.presentation.films_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.github.sergey_kornyushin.databinding.FragmentFilmsListBinding
import com.github.sergey_kornyushin.presentation.extentions.snackbar
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmsAdapter
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmsSpanSize
import com.github.sergey_kornyushin.presentation.films_list.recycler_view.interfaces.RVClickListener
import com.github.sergey_kornyushin.presentation.ui.ViewBindingHolder
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class FilmsListFragment : MvpAppCompatFragment(), FilmsListView, RVClickListener {

    @Inject
    lateinit var rvFilmsAdapter: RVFilmsAdapter

    @Inject
    lateinit var presenterProvider: Provider<FilmsListPresenter.Base>
    private val presenter by moxyPresenter { presenterProvider.get() }

    private val bindingHolder = ViewBindingHolder<FragmentFilmsListBinding>()
    private val binding get() = bindingHolder.binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = bindingHolder.createView(
        viewLifecycleOwner
    ) { FragmentFilmsListBinding.inflate(inflater, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvFilmsAdapter.setClickListener(this)
        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.spanSizeLookup = RVFilmsSpanSize(rvFilmsAdapter)

        binding.rvFilmsList.apply {
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = rvFilmsAdapter
        }
    }

    override fun showError(message: String) {
        binding.root.snackbar(message)
    }

    override fun showLoading(isLoading: Boolean) {
        binding.pbDownload.isVisible = isLoading
    }

    override fun fillRVList(filmsList: MutableList<RVFilmItem>) {
        rvFilmsAdapter.list = filmsList
    }

    override fun genreClick(genre: RVFilmItem.Genre, position: Int) {
        presenter.sortFilmsByGenre(genre, position)
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