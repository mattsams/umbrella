package io.mattsams.umbrella.presentation.main

import dagger.Module
import dagger.Provides
import io.mattsams.umbrella.UmbrellaPreferencesImpl
import io.mattsams.umbrella.data.api.WeatherUndergroundApi
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Module
class MainModule {
    @Provides
    @Singleton
    fun providesInteractor(api: WeatherUndergroundApi, prefs: UmbrellaPreferencesImpl): MainInteractor =
            MainInteractorImpl(api, prefs)

    @Provides
    @Singleton
    fun providesPresenter(
            interactor: MainInteractor,
            @Named("mainScheduler") scheduler: Scheduler
    ): MainPresenter = MainPresenterImpl(interactor, scheduler)
}