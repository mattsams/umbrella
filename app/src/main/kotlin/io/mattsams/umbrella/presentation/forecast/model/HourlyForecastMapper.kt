package io.mattsams.umbrella.presentation.forecast.model

import io.mattsams.umbrella.UmbrellaPreferences
import io.mattsams.umbrella.Units
import io.mattsams.umbrella.data.model.HourlyForecast
import org.threeten.bp.LocalTime

class HourlyForecastMapper(private val prefs: UmbrellaPreferences) {
    fun map(forecast: HourlyForecast, highlight: IconHighlight): HourlyForecastModel {
        return HourlyForecastModel(
                time = LocalTime.of(forecast.fctTime.hour, forecast.fctTime.min),
                temp = when (prefs.units) {
                    Units.IMPERIAL -> Math.round(forecast.temp.english).toInt()
                    Units.METRIC -> Math.round(forecast.temp.metric).toInt()
                },
                icon = forecast.icon,
                highlight = highlight
        )
    }
}