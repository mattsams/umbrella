package io.mattsams.umbrella.presentation.forecast

import io.mattsams.umbrella.UmbrellaPreferences
import io.mattsams.umbrella.UmbrellaPreferencesImpl
import io.mattsams.umbrella.data.api.WeatherUndergroundApi
import io.mattsams.umbrella.presentation.forecast.model.DailyForecastMapper
import io.mattsams.umbrella.presentation.forecast.model.DailyForecastModel
import io.reactivex.Single

class ForecastInteractorImpl(
        private val api: WeatherUndergroundApi,
        private val prefs: UmbrellaPreferences
) : ForecastInteractor {
    private val dailyMapper = DailyForecastMapper(prefs)

    override fun fetchForecast(): Single<List<DailyForecastModel>> {
        val postalCode = prefs.postalCode ?: UmbrellaPreferencesImpl.DEFAULT_POSTAL_CODE
        return Single.fromObservable(api.hourly(postalCode).map {
            dailyMapper.map(it.hourlyForecast)
        })
    }
}