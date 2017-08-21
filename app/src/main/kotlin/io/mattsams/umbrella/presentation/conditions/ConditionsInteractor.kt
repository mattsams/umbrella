package io.mattsams.umbrella.presentation.conditions

import io.mattsams.umbrella.mvp.Interactor
import io.mattsams.umbrella.presentation.conditions.model.CurrentConditionsModel
import io.reactivex.Single

interface ConditionsInteractor : Interactor {
    fun fetchCurrent(): Single<CurrentConditionsModel>
}