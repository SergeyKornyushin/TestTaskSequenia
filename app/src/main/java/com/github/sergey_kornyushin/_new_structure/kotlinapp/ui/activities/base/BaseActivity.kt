package com.github.sergey_kornyushin._new_structure.kotlinapp.ui.activities.base

import moxy.MvpAppCompatActivity

abstract class BaseActivity(
    @androidx.annotation.LayoutRes contentLayoutId: Int
) : MvpAppCompatActivity(contentLayoutId)