package io.mattsams.umbrella.presentation.forecast

import io.mattsams.umbrella.mvp.Interactor
import io.mattsams.umbrella.presentation.conditions.model.CurrentConditionsModel
import io.mattsams.umbrella.presentation.forecast.model.DailyForecastModel
import io.reactivex.Single

interface ForecastInteractor : Interactor {
    fun fetchCurrent(postalCode: String): Single<CurrentConditionsModel>
    fun fetchForecast(postalCode: String): Single<List<DailyForecastModel>>
}