package com.github.sergey_kornyushin.presentation.film_page

import com.github.sergey_kornyushin.domain.model.Film
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FilmPageView : MvpView{
    fun showLoading(isLoading: Boolean)
    fun showError(message: String)
    fun showFilm(film: Film)
}