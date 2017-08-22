package io.mattsams.umbrella.presentation.forecast

import io.mattsams.umbrella.EventBus
import io.mattsams.umbrella.InvalidData
import io.mattsams.umbrella.RefreshData
import io.mattsams.umbrella.mvp.SimplePresenter
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ForecastPresenterImpl(private val interactor: ForecastInteractor, private val scheduler: Scheduler)
    : SimplePresenter<ForecastView>(), ForecastPresenter {

    override fun attach(view: ForecastView) {
        super.attach(view)
        rx(
                EventBus.listen<RefreshData>()
                        .subscribeOn(Schedulers.io())
                        .observeOn(scheduler)
                        .subscribe({
                            load()
                        }, {
                            Timber.e(it)
                        })
        )
    }

    private fun load() {
        rx(
                interactor.fetchForecast()
                        .subscribeOn(Schedulers.io())
                        .observeOn(scheduler)
                        .subscribe({
                            view()?.loadForecast(it)
                        }, {
                            Timber.e(it)
                            EventBus.emit(InvalidData("Unable to load forecast."))
                        })
        )
    }
}