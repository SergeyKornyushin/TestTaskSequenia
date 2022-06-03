package com.github.sergey_kornyushin._new_structure.kotlinapp.ui.fragments.base

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

open class BaseNavigationFragment(
    @androidx.annotation.LayoutRes contentLayoutId: Int
) : BaseFragment(contentLayoutId) {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavigationController()
    }

    override fun onBackPressed(shouldHideKeyboard: Boolean) {
        super.onBackPressed(shouldHideKeyboard)

        val isPopped = navController.popBackStack()

        if (isPopped) {
            return
        }

        activity?.finish()
    }

    protected fun navigate(actionId: Int) {
        navController.navigate(actionId)
    }

    private fun initNavigationController() {
        navController = findNavController()
    }
}