package io.mattsams.umbrella.data.model

import com.google.gson.annotations.SerializedName

/**
 * Encapsulates the hourly API response from https://www.wunderground.com/weather/api/d/docs?d=data/hourly.
 * Only the fields we are interested in have been included.
 */
data class HourlyResponse(
        @SerializedName("hourly_forecast")
        val hourlyForecast: List<HourlyForecast>
)