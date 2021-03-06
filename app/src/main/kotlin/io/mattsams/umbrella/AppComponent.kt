package io.mattsams.umbrella

import dagger.Component
import io.mattsams.umbrella.data.DataModule
import io.mattsams.umbrella.presentation.PresentationModule
import io.mattsams.umbrella.view.MainActivity
import io.mattsams.umbrella.view.UmbrellaPreferencesActivity
import io.mattsams.umbrella.view.conditions.ConditionsWidget
import io.mattsams.umbrella.view.forecast.ForecastLayout
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, DataModule::class, PresentationModule::class))
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(conditionsView: ConditionsWidget)
    fun inject(forecastView: ForecastLayout)
    fun inject(preferences: UmbrellaPreferencesActivity.UmbrellaPreferencesFragment)
}