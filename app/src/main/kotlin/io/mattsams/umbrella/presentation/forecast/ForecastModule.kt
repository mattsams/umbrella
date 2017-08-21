package io.mattsams.umbrella.presentation.forecast

import dagger.Module
import dagger.Provides
import io.mattsams.umbrella.UmbrellaPreferencesImpl
import io.mattsams.umbrella.data.api.WeatherUndergroundApi
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Module
class ForecastModule {
    @Provides
    @Singleton
    fun providesInteractor(api: WeatherUndergroundApi, prefs: UmbrellaPreferencesImpl): ForecastInteractor =
            ForecastInteractorImpl(api, prefs)

    @Provides
    @Singleton
    fun providesPresenter(
            interactor: ForecastInteractor,
            @Named("mainScheduler") scheduler: Scheduler
    ): ForecastPresenter = ForecastPresenterImpl(interactor, scheduler)
}