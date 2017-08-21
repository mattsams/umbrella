package io.mattsams.umbrella.presentation.main.model

import io.mattsams.umbrella.UmbrellaPreferencesImpl
import io.mattsams.umbrella.data.model.HourlyForecast
import org.threeten.bp.LocalDate

class DailyForecastMapper(prefs: UmbrellaPreferencesImpl) {
    private val hourlyMapper = HourlyForecastMapper(prefs)

    fun map(forecast: List<HourlyForecast>): List<DailyForecastModel> {
        return forecast
                .groupBy { it.fctTime.yday }
                .map {
                    val first = it.value.first()
                    val date = LocalDate.of(first.fctTime.year, first.fctTime.mon, first.fctTime.mday)
                    var lowest = it.value.minBy { it.temp.english }
                    var highest = it.value.maxBy { it.temp.english }
                    if (lowest == highest) {
                        lowest = null
                        highest = null
                    }

                    val hourlies = it.value.map {
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