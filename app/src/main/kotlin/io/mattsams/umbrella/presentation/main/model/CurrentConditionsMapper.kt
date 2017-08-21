package io.mattsams.umbrella.presentation.main.model

import io.mattsams.umbrella.R
import io.mattsams.umbrella.UmbrellaPreferences
import io.mattsams.umbrella.Units
import io.mattsams.umbrella.data.model.CurrentObservation

class CurrentConditionsMapper(private val prefs: UmbrellaPreferences) {
    fun map(current: CurrentObservation): CurrentConditionsModel {
        return CurrentConditionsModel(
                location = current.displayLocation.full,
                conditions = current.weather,
                temperature = when (prefs.units) {
                    Units.IMPERIAL -> current.tempF
                    Units.METRIC -> current.tempC
                },
                colorId = if (current.tempF >= 60) R.color.warm else R.color.cool
        )
    }
}