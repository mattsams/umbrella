package io.mattsams.umbrella.presentation.main

import io.mattsams.umbrella.UmbrellaPreferencesImpl
import io.mattsams.umbrella.data.api.WeatherUndergroundApi
import io.mattsams.umbrella.presentation.main.model.CurrentConditionsMapper
import io.mattsams.umbrella.presentation.main.model.CurrentConditionsModel
import io.mattsams.umbrella.presentation.main.model.DailyForecastMapper
import io.mattsams.umbrella.presentation.main.model.DailyForecastModel
import io.reactivex.Single

class MainInteractorImpl(
        val api: WeatherUndergroundApi,
        prefs: UmbrellaPreferencesImpl
) : MainInteractor {
    private val currentMapper = CurrentConditionsMapper(prefs)
    private val dailyMapper = DailyForecastMapper(prefs)

    override fun fetchCurrent(postalCode: String): Single<CurrentConditionsModel> {
        return Single.fromObservable(api.current(postalCode).map {
            currentMapper.map(it.currentObservation)
        })
    }

    override fun fetchForecast(postalCode: String): Single<List<DailyForecastModel>> {
        return Single.fromObservable(api.hourly(postalCode).map {
            dailyMapper.map(it.hourlyForecast)
        })
    }
}