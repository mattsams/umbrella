package io.mattsams.umbrella.data.api

import dagger.Module
import dagger.Provides
import io.mattsams.umbrella.AppScope
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    @Named("API_URL")
    fun providesApiURL() = "http://api.wunderground.com"
}