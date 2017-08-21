package io.mattsams.umbrella.presentation.conditions

import android.support.annotation.ColorRes
import io.mattsams.umbrella.mvp.MvpView

interface ConditionsView : MvpView {
    fun setTemperature(temperature: String)
    fun setConditions(conditions: String)
    fun updateColor(@ColorRes colorId: Int)
}