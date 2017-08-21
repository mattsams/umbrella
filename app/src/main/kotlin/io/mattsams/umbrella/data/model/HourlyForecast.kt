package io.mattsams.umbrella.data.model

import com.google.gson.annotations.SerializedName

/**
 * Encapsulates a single 'hourly_forecast' entry of the hourly API response.
 */
data class HourlyForecast(
        @SerializedName("FCTTIME")
        val fctTime: FctTime,
        @SerializedName("temp")
        val temp: Temperature,
        @SerializedName("icon")
        val icon: String
)