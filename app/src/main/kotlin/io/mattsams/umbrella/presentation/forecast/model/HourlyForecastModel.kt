package io.mattsams.umbrella.presentation.forecast.model

import org.threeten.bp.LocalTime

data class HourlyForecastModel(
        val time: LocalTime,
        val temp: Int,
        val icon: String,
        val highlight: IconHighlight = IconHighlight.NONE
)