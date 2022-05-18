package com.github.sergey_kornyushin.presentation.films_list

import com.github.sergey_kornyushin.presentation.films_list.recycler_view.RVFilmItem
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEnd
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FilmsListView : MvpView{
    fun showError(message: String)
    fun showLoading(isLoading: Boolean)
    fun fillRVList(filmsList: List<RVFilmItem>)
}