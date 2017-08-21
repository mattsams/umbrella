package io.mattsams.umbrella.presentation.conditions

import dagger.Module
import dagger.Provides
import io.mattsams.umbrella.UmbrellaPreferences
import io.mattsams.umbrella.data.api.WeatherUndergroundApi
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Module
class ConditionsModule {
    @Provides
    @Singleton
    fun providesInteractor(api: WeatherUndergroundApi, prefs: UmbrellaPreferences): ConditionsInteractor =
            ConditionsInteractorImpl(api, prefs)

    @Provides
    @Singleton
    fun providesPresenter(
            interactor: ConditionsInteractor,
            @Named("mainScheduler") scheduler: Scheduler
    ): ConditionsPresenter
            = ConditionsPresenterImpl(interactor, scheduler)
}