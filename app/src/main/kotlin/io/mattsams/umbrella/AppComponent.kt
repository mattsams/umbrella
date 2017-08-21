package io.mattsams.umbrella

import dagger.Component
import io.mattsams.umbrella.data.DataModule
import io.mattsams.umbrella.presentation.main.MainView
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, DataModule::class))
@Singleton
interface AppComponent {
    fun inject(mainView: MainView)
}