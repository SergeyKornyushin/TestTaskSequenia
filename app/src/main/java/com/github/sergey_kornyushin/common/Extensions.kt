package com.github.sergey_kornyushin.common

fun Int?.avoidNullToString(string: String): String {
    return this?.toString() ?: string
}

fun Double?.avoidNullToString(string: String): String {
    return if (this == null || this == -1.0) string else this.toString()
}

fun String?.avoidNullToString(string: String): String {
    return if (this.isNullOrEmpty()) string else return this
}