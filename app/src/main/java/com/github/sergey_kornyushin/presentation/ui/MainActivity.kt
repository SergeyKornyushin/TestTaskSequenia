package com.github.sergey_kornyushin.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.github.sergey_kornyushin.R
import com.github.sergey_kornyushin.data.di.NetworkModule
import com.github.sergey_kornyushin.data.remote.FilmsApi
import com.github.sergey_kornyushin.data.repository.FilmsRepositoryImpl
import com.github.sergey_kornyushin.domain.repository.FilmsRepository
import com.github.sergey_kornyushin.domain.use_cases.get_films.GetFilmsUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repo: FilmsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val filmsUseCase = GetFilmsUseCase(repo)


        lifecycleScope.launchWhenCreated {

            filmsUseCase.getFilms().collect { value ->
                Log.i("test4", "onCreate: ${value.data} ${value.message}")
            }
        }
    }
}