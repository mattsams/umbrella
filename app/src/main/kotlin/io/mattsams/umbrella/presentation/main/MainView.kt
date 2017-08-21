package io.mattsams.umbrella.presentation.main

import io.mattsams.umbrella.mvp.MvpView
import io.mattsams.umbrella.presentation.main.model.CurrentConditionsModel
import io.mattsams.umbrella.presentation.main.model.DailyForecastModel

interface MainView : MvpView {
    fun loadCurrentConditions(currentConditions: CurrentConditionsModel)
    fun loadForecast(forecast: List<DailyForecastModel>)
}