package com.github.sergey_kornyushin._new_structure.kotlinapp.mvp.models.base

interface ErrorCallback {
    /**
     * Срабатывает при ошибке
     *
     * @param error текст ошибки
     */
    fun onError(error: String)
}