package io.mattsams.umbrella.presentation

import dagger.Module
import io.mattsams.umbrella.presentation.main.MainModule
import io.mattsams.umbrella.presentation.main.conditions.ConditionsModule

@Module(includes = arrayOf(MainModule::class, ConditionsModule::class))
class PresentationModule