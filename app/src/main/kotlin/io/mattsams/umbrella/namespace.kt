package io.mattsams.umbrella

import android.content.Context
import android.net.ConnectivityManager
import android.view.View

fun Context.app() = UmbrellaApp.get(this)

fun Context.injector() = app().graph

fun View.injector() = context.injector()

fun Int.toDegrees() = "${this}°"

fun Context.pxToDp(px: Double): Int = (px / resources.displayMetrics.density).toInt()

fun Context.dpToPx(dp: Double): Int = (dp * resources.displayMetrics.density).toInt()

fun Context.isNetworkAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo?.isConnected ?: false
}