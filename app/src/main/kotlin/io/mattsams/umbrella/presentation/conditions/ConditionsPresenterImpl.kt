package io.mattsams.umbrella.presentation.conditions

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
                interactor
                        .fetchCurrent()
                        .subscribeOn(Schedulers.io())
                        .observeOn(scheduler)
                        .subscribe({
                            updateConditions(it)
                        }, {
                            Timber.e(it)
                        })
        )
    }

    private fun updateConditions(conditions: CurrentConditionsModel) {
        view()?.let { view ->
            view.setLocation(conditions.location)
            view.setTemperature(conditions.temperature.toDegrees())
            view.setConditions(conditions.conditions)
            view.updateColor(conditions.colorId)
        }
    }
}