package com.github.sergey_kornyushin._new_structure.kotlinapp.ui.activities.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.github.sergey_kornyushin.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

abstract class BaseWithAppBarNavigationActivity(
    @androidx.annotation.LayoutRes contentLayoutId: Int = R.layout.base_with_app_bar_activity
) : BaseNavigationActivity(contentLayoutId), AppBarProvider,
    AppBarViews {

    private lateinit var appBarProvider: AppBarProviderImp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAppBar()
    }

    override fun getAppBarProviderImp(): AppBarProviderImp = appBarProvider

    override fun getCollapsingContent(): ViewGroup = findViewById(R.id.collapsingView)

    override fun getAppBar(): AppBarLayout = findViewById(R.id.appBar)

    override fun getToolbar(): Toolbar = findViewById(R.id.toolbar)

    override fun getCollapsingToolbarLayout(): CollapsingToolbarLayout =
        findViewById(R.id.collapsingToolbarLayout)

    private fun initAppBar() {
        appBarProvider = AppBarProviderImp(this)
        setSupportActionBar(toolbar)
    }
}