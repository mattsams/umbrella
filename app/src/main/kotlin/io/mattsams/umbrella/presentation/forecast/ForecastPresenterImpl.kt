package io.mattsams.umbrella.presentation.forecast

import io.mattsams.umbrella.mvp.SimplePresenter
import io.reactivex.Scheduler

class ForecastPresenterImpl(private val interactor: ForecastInteractor, private val scheduler: Scheduler)
    : SimplePresenter<ForecastView>(), ForecastPresenter {


}