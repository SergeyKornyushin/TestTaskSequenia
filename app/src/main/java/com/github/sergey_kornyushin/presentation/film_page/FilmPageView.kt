package com.github.sergey_kornyushin.presentation.film_page

import com.github.sergey_kornyushin.domain.model.Film
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.OneExecution

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FilmPageView : MvpView{

    @OneExecution
    fun showError(message: String)
    fun showLoading(isLoading: Boolean)
    fun showFilm(film: Film)
}