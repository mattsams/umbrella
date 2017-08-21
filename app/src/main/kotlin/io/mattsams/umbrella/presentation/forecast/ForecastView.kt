package io.mattsams.umbrella.presentation.forecast

import io.mattsams.umbrella.mvp.MvpView
import io.mattsams.umbrella.presentation.forecast.model.DailyForecastModel

interface ForecastView : MvpView {
    fun loadForecast(forecast: List<DailyForecastModel>)
}