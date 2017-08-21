package io.mattsams.umbrella

import android.content.Context
import android.view.View

fun Context.app() = UmbrellaApp.get(this)

fun Context.injector() = app().graph

fun View.injector() = context.injector()

fun Int.toDegrees() = "${this}°"
