package com.github.sergey_kornyushin._new_structure.kotlinapp.mvp.views

import moxy.viewstate.strategy.AddToEndSingleTagStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ContentLoadingView : BaseView {

    /**
     * Начата загрузка контента
     *
     * Очищает весь ViewState
     */
    @StateStrategyType(value = SingleStateStrategy::class, tag = "contentLoading")
    fun startContentLoading()

    /**
     * Загрузка контента завершена
     */
    @StateStrategyType(value = AddToEndSingleTagStrategy::class, tag = "contentLoading")
    fun endContentLoading()

    /**
     * Показать ошибку контента
     *
     * @param error текст ошибки
     */
    @StateStrategyType(AddToEndSingleTagStrategy::class)
    fun showContentLoadingError(error: String)

}

