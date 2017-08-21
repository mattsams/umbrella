package io.mattsams.umbrella

import dagger.Component
import io.mattsams.umbrella.data.DataModule
import io.mattsams.umbrella.presentation.PresentationModule
import io.mattsams.umbrella.view.main.conditions.ConditionsWidget
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, DataModule::class, PresentationModule::class))
@Singleton
interface AppComponent {
    fun inject(conditionsView: ConditionsWidget)
}