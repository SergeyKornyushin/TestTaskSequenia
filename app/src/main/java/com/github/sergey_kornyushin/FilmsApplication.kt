package com.github.sergey_kornyushin

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm

@HiltAndroidApp
class FilmsApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}