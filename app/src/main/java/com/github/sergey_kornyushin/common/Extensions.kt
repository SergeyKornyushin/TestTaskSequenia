package com.github.sergey_kornyushin.common

fun Int?.avoidNullToString(): String {
    return this?.toString() ?: "Unknown"
}

fun Double?.avoidNullToString(): String {
    return if (this == null || this == -1.0) "Unknown" else this.toString()
}

fun String?.avoidNullToString(): String {
    return if (this.isNullOrEmpty()) "Unknown" else return this
}