package io.mattsams.umbrella.data

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.mattsams.umbrella.data.api.ApiModule
import io.mattsams.umbrella.data.api.WeatherUndergroundApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = arrayOf(ApiModule::class))
class DataModule {
    @Provides
    @Singleton
    fun providesRetrofit(gson: Gson, @Named("API_URL") baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Provides
    @Singleton
    fun providesWeatherUndergroundApi(retrofit: Retrofit): WeatherUndergroundApi {
        return retrofit.create(WeatherUndergroundApi::class.java)
    }
}