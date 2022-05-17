package com.github.sergey_kornyushin.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.domain.model.Film
import com.github.sergey_kornyushin.domain.model.Genre
import com.github.sergey_kornyushin.domain.repository.FilmsRepository
import com.github.sergey_kornyushin.domain.repository.SelectedFilmRepository
import com.github.sergey_kornyushin.domain.repository.SortRepository
import com.github.sergey_kornyushin.domain.use_cases.get_films.GetFilmsUseCase
import com.github.sergey_kornyushin.domain.use_cases.get_selected_film.GetSelectedFilmUseCase
import com.github.sergey_kornyushin.domain.use_cases.sort_films.SortFilmsByGenreUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repo: FilmsRepository

    @Inject
    lateinit var sortRepo: SortRepository

    @Inject
    lateinit var getSelectedRepo: SelectedFilmRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val filmsUseCase = GetFilmsUseCase(repo)

        val genre = Genre("триллер")
        val sortUseCase = SortFilmsByGenreUseCase(sortRepo)

        val film = Film(filmId = 426364)
        val getSelectedUseCase = GetSelectedFilmUseCase(getSelectedRepo)

        lifecycleScope.launchWhenCreated {
            filmsUseCase.getFilms().collect { value ->
                Log.i("test4", "getFilms: ${value.data} | message ${value.message}")
            }

            sortUseCase.getFilmsByGenre(genre).collect { value ->
                Log.i("test4", "getFilmsByGenre: ${value.data} | message ${value.message}")
            }

            getSelectedUseCase.getSelectedFilmById(film).collect { value ->
                Log.i("test4", "getSelectedFilm: ${value.data} | message ${value.message}")
            }
        }
    }
}