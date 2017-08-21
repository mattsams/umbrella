package io.mattsams.umbrella.presentation.forecast

import io.mattsams.umbrella.mvp.SimplePresenter
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ForecastPresenterImpl(private val interactor: ForecastInteractor, private val scheduler: Scheduler)
    : SimplePresenter<ForecastView>(), ForecastPresenter {

    override fun attach(view: ForecastView) {
        super.attach(view)

        rx(
                interactor.fetchForecast()
                        .subscribeOn(Schedulers.io())
                        .observeOn(scheduler)
                        .subscribe({
                            view()?.loadForecast(it)
                        }, {
                            Timber.e(it)
                        })
        )
    }
}