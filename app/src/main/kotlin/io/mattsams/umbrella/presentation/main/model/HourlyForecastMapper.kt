package io.mattsams.umbrella.presentation.main.model

import io.mattsams.umbrella.UmbrellaPreferencesImpl
import io.mattsams.umbrella.Units
import io.mattsams.umbrella.data.model.HourlyForecast
import org.threeten.bp.LocalTime

class HourlyForecastMapper(private val prefs: UmbrellaPreferencesImpl) {
    fun map(forecast: HourlyForecast, highlight: IconHighlight): HourlyForecastModel {
        return HourlyForecastModel(
                time = LocalTime.of(forecast.fctTime.hour, forecast.fctTime.min),
                temp = when (prefs.units) {
                    Units.IMPERIAL -> forecast.temp.english
                    Units.METRIC -> forecast.temp.metric
                },
                icon = forecast.icon,
                highlight = highlight
        )
    }
}