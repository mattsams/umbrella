package io.mattsams.umbrella.presentation.conditions

import io.mattsams.umbrella.UmbrellaPreferences
import io.mattsams.umbrella.UmbrellaPreferencesImpl
import io.mattsams.umbrella.data.api.WeatherUndergroundApi
import io.mattsams.umbrella.presentation.conditions.model.CurrentConditionsMapper
import io.mattsams.umbrella.presentation.conditions.model.CurrentConditionsModel
import io.reactivex.Single

class ConditionsInteractorImpl(
        private val api: WeatherUndergroundApi,
        private val prefs: UmbrellaPreferences
) : ConditionsInteractor {
    private val currentMapper = CurrentConditionsMapper(prefs)

    override fun fetchCurrent(): Single<CurrentConditionsModel> {
        val postalCode = prefs.postalCode ?: UmbrellaPreferencesImpl.DEFAULT_POSTAL_CODE
        return Single.fromObservable(api.current(postalCode).map {
            currentMapper.map(it.currentObservation)
        })
    }
}