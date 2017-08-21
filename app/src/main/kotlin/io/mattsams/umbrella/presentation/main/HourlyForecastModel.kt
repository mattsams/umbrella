package io.mattsams.umbrella.presentation.main

import org.threeten.bp.LocalTime

data class HourlyForecastModel(
        val time: LocalTime,
        val temp: Int,
        val icon: String,
        val highlight: IconHighlight = IconHighlight.NONE
)