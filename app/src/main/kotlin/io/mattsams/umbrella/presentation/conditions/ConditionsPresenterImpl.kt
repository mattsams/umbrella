package io.mattsams.umbrella.presentation.conditions

import io.mattsams.umbrella.EventBus
import io.mattsams.umbrella.InvalidData
import io.mattsams.umbrella.RefreshData
import io.mattsams.umbrella.mvp.SimplePresenter
import io.mattsams.umbrella.presentation.conditions.model.CurrentConditionsModel
import io.mattsams.umbrella.toDegrees
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ConditionsPresenterImpl(
        private val interactor: ConditionsInteractor,
        private val scheduler: Scheduler
) : SimplePresenter<ConditionsView>(), ConditionsPresenter {

    override fun attach(view: ConditionsView) {
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
                interactor
                        .fetchCurrent()
                        .subscribeOn(Schedulers.io())
                        .observeOn(scheduler)
                        .subscribe({
                            updateConditions(it)
                        }, {
                            Timber.e(it)
                            EventBus.emit(InvalidData("Unable to load conditions."))
                        })
        )
    }

    private fun updateConditions(conditions: CurrentConditionsModel) {
        view()?.let { view ->
            view.setLocation(conditions.location)
            view.setTemperature(conditions.temperature.toDegrees())
            view.setConditions(conditions.conditions)
            view.updateColor(conditions.colorId, true)
        }
    }
}