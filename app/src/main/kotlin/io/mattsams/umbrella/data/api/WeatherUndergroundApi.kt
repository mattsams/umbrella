package io.mattsams.umbrella.data.api

import io.mattsams.umbrella.BuildConfig
import io.mattsams.umbrella.data.model.ConditionsResponse
import io.mattsams.umbrella.data.model.HourlyResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherUndergroundApi {
    @GET("/api/${BuildConfig.API_KEY}/conditions/q/{postalCode}.json")
    fun current(@Path("postalCode") postalCode: String): Observable<ConditionsResponse>

    @GET("/api/${BuildConfig.API_KEY}/hourly/q/{postalCode}.json")
    fun hourly(@Path("postalCode") postalCode: String): Observable<HourlyResponse>
}