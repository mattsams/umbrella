package io.mattsams.umbrella.data.model

import com.google.gson.annotations.SerializedName

data class CurrentObservation(
        @SerializedName("display_location")
        val displayLocation: DisplayLocation,
        @SerializedName("temp_f")
        val tempF: Double,
        @SerializedName("temp_c")
        val tempC: Double,
        @SerializedName("weather")
        val weather: String
)