package com.github.sergey_kornyushin._new_structure.kotlinapp.mvp.models.base

interface BaseCallback<Type> : ErrorCallback, SuccessCallback<Type> {
    override fun onSuccess(data: Type)

    override fun onError(error: String)
}