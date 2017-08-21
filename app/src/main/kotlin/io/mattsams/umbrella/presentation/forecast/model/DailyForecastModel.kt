package io.mattsams.umbrella.presentation.forecast.model

import org.threeten.bp.LocalDate

data class DailyForecastModel(
        val date: LocalDate,
        val hourly: List<HourlyForecastModel>
)