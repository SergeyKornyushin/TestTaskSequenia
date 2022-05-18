package com.github.sergey_kornyushin.common

import android.content.Context
import android.content.res.Resources
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ResourceProvider {
    fun getResources(): Resources
    fun getString(stringId: Int): String

    class Base @Inject constructor(
        @ApplicationContext private val context: Context
    ) : ResourceProvider {
        override fun getResources(): Resources {
            return context.resources
        }

        override fun getString(stringId: Int): String {
            return context.resources.getString(stringId)
        }
    }
}

