package io.mattsams.umbrella.presentation

import dagger.Module
import io.mattsams.umbrella.presentation.forecast.ForecastModule
import io.mattsams.umbrella.presentation.conditions.ConditionsModule

@Module(includes = arrayOf(ForecastModule::class, ConditionsModule::class))
class PresentationModule