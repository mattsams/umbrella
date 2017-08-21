package io.mattsams.umbrella.presentation.main

import io.mattsams.umbrella.UmbrellaPreferences
import io.mattsams.umbrella.data.api.WeatherUndergroundApi
import io.reactivex.Single

class MainInteractorImpl(
        val api: WeatherUndergroundApi,
        val prefs: UmbrellaPreferences
) : MainInteractor {
    override fun fetchCurrent(postalCode: String): Single<CurrentConditionsModel> {
        return Single.fromObservable(api.current(postalCode).map {
            CurrentConditionsModel(
                    location = it.currentObservation.weather,
                    temperature = it.currentObservation.tempC,
                    conditions = it.currentObservation.weather
            )
        })
    }

    override fun fetchForecast(postalCode: String): Single<List<DailyForecastModel>> {
        TODO()
        /*
        return Single.fromObservable(api.hourly(postalCode).map {
        })
        */
    }
}