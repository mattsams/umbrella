package io.mattsams.umbrella.presentation.main.model

import android.support.annotation.ColorRes

data class CurrentConditionsModel(
        val location: String,
        val temperature: Int,
        val conditions: String,
        @ColorRes val colorId: Int
)