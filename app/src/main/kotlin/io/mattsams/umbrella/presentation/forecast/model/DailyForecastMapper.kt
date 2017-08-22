package io.mattsams.umbrella.presentation.forecast.model

import io.mattsams.umbrella.UmbrellaPreferences
import io.mattsams.umbrella.data.model.HourlyForecast
import org.threeten.bp.LocalDate

class DailyForecastMapper(prefs: UmbrellaPreferences) {
    private val hourlyMapper = HourlyForecastMapper(prefs)

    fun map(forecast: List<HourlyForecast>): List<DailyForecastModel> {
        return forecast
                .groupBy { it.fctTime.yday }
                .map {
                    val first = it.value.first()
                    val date = LocalDate.of(first.fctTime.year, first.fctTime.mon, first.fctTime.mday)
                    val ordered = it.value.sortedBy { it.fctTime.hour }
                    var lowest = ordered.minBy { it.temp.english }
                    var highest = ordered.maxBy { it.temp.english }
                    if (lowest == highest) {
                        lowest = null
                        highest = null
                    }

                    val hourlies = ordered.map {
                        hourlyMapper.map(it, when (it) {
                            lowest -> IconHighlight.COOL
                            highest -> IconHighlight.WARM
                            else -> IconHighlight.NONE
                        })
                    }

                    DailyForecastModel(
                            date = date,
                            hourly = hourlies
                    )
                }
    }
}