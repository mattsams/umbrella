package io.mattsams.umbrella.presentation.main

import io.mattsams.umbrella.mvp.Interactor
import io.reactivex.Single

interface MainInteractor : Interactor {
    fun fetchCurrent(postalCode: String): Single<CurrentConditionsModel>
    fun fetchForecast(postalCode: String): Single<List<DailyForecastModel>>
}