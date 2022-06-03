package com.github.sergey_kornyushin._new_structure.kotlinapp.ui.activities.base

import android.os.Bundle
import androidx.navigation.NavController
import com.github.sergey_kornyushin.R

abstract class BaseNavigationActivity(
    @androidx.annotation.LayoutRes contentLayoutId: Int = R.layout.base_activity
) : BaseActivity(contentLayoutId) {

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigationController()
    }

    protected open fun getNavigationViewId() = R.id.navigation_view

    protected fun setGraph(graphLayoutId: Int) {
        navigationController.setGraph(graphLayoutId)
    }

    protected fun setGraph(graphLayoutId: Int, startDestinationId: Int) {
        setGraph(graphLayoutId, startDestinationId, null)
    }

    protected fun setGraph(graphLayoutId: Int, startDestinationId: Int, data: Bundle?) {
        val graph = navigationController.navInflater.inflate(graphLayoutId)
        graph.startDestination = startDestinationId
        navigationController.setGraph(graph, data)
    }

    private fun initNavigationController() {
        navigationController = findNavController(getNavigationViewId())
    }
}