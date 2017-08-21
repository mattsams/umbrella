package io.mattsams.umbrella.presentation.conditions.model

import android.support.annotation.ColorRes

data class CurrentConditionsModel(
        val location: String,
        val temperature: Int,
        val conditions: String,
        @ColorRes val colorId: Int
)