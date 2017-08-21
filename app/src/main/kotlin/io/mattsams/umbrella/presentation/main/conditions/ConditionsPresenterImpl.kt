package io.mattsams.umbrella.presentation.main.conditions

import io.mattsams.umbrella.EventBus
import io.mattsams.umbrella.mvp.SimplePresenter
import io.mattsams.umbrella.temperatureColor
import io.mattsams.umbrella.toDegrees
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class ConditionsPresenterImpl(private val scheduler: Scheduler)
    : SimplePresenter<ConditionsView>(), ConditionsPresenter {

    override fun attach(view: ConditionsView) {
        super.attach(view)

        rx(
                EventBus.listen<ConditionsChanged>()
                        .subscribeOn(Schedulers.io())
                        .observeOn(scheduler)
                        .subscribe({
                            updateConditions(it)
                        })
        )
    }

    private fun updateConditions(conditionsChanged: ConditionsChanged) {
        view()?.let { view ->
            view.setTemperature(conditionsChanged.temperature.toDegrees())
            view.setConditions(conditionsChanged.conditions)
            view.updateColor(temperatureColor(conditionsChanged.temperature))
        }
    }
}