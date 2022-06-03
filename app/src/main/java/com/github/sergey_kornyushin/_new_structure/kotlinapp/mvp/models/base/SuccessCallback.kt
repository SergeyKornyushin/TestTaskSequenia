package com.github.sergey_kornyushin._new_structure.kotlinapp.mvp.models.base

interface SuccessCallback<Type> {
    /**
     * Срабатывает при успехе
     *
     * @param data полученные данные
     */
    fun onSuccess(data: Type)
}
