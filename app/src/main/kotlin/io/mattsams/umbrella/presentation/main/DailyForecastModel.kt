package io.mattsams.umbrella.presentation.main

import org.threeten.bp.LocalDate

data class DailyForecastModel(
        val date: LocalDate,
        val hourly: List<HourlyForecastModel>
)