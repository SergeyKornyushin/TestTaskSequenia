package com.github.sergey_kornyushin._new_structure.kotlinapp.mvp.presenters

import com.github.sergey_kornyushin._new_structure.kotlinapp.mvp.views.BaseView
import moxy.MvpPresenter

open class BasePresenter<View : BaseView> : MvpPresenter<View>()