package io.mattsams.umbrella.presentation.main

import io.mattsams.umbrella.mvp.MvpView

interface MainView : MvpView {
    fun loadForecastData(data: List<DailyForecastModel>)
}