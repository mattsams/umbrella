package io.mattsams.umbrella.presentation.main.conditions

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Module
class ConditionsModule {
    @Provides
    @Singleton
    fun providesPresenter(@Named("mainScheduler") scheduler: Scheduler): ConditionsPresenter
            = ConditionsPresenterImpl(scheduler)
}