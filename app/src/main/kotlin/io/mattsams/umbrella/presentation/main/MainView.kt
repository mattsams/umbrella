package io.mattsams.umbrella.presentation.main

import io.mattsams.umbrella.mvp.MvpView

interface MainView : MvpView {
    fun loadCurrentConditions(currentConditions: CurrentConditionsModel)
    fun loadForecast(forecast: List<DailyForecastModel>)
}