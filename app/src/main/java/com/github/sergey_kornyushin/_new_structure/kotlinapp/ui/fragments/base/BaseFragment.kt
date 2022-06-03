package com.github.sergey_kornyushin._new_structure.kotlinapp.ui.fragments.base

import android.view.Window
import moxy.MvpAppCompatFragment

abstract class BaseFragment(
    @androidx.annotation.LayoutRes contentLayoutId: Int
) : MvpAppCompatFragment(contentLayoutId), ScreenLocker {

    private val keyboardEventManager = KeyboardEventManager()

    override fun getWindow(): Window? {
        return activity?.window
    }

    /**
     * Аналог метода [Activity.onBackPressed] для [androidx.fragment.app.Fragment]
     */
    protected open fun onBackPressed() {
        onBackPressed(true)
    }

    /**
     * Аналог метода [Activity.onBackPressed] для [androidx.fragment.app.Fragment]
     *
     * @param shouldHideKeyboard true - клавиатура будет скрыта, иначе остается в текущем состояние
     */
    protected open fun onBackPressed(shouldHideKeyboard: Boolean) {
        if (!shouldHideKeyboard) {
            return
        }

        hideKeyboard()
    }

    /**
     * Скрытие клавиатуры
     */
    protected fun hideKeyboard() {
        keyboardEventManager.hideKeyboard(activity)
    }

    /**
     * Показ клавиатуры
     */
    protected fun showKeyboard() {
        keyboardEventManager.showKeyboard(activity)
    }

    /**
     * Добавляет слушатель видимости клавиатуры
     *
     * @param listener [слушатель видимости клавиатуры][KeyboardVisibilityListener]
     */
    protected fun addKeyboardVisibilityListener(listener: KeyboardVisibilityListener) {
        keyboardEventManager.setKeyboardVisibilityListener(
            activity,
            viewLifecycleOwner,
            listener
        )
    }
}