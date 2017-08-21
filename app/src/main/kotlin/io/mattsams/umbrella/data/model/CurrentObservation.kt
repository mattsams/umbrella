package io.mattsams.umbrella.data.model

import com.google.gson.annotations.SerializedName

data class CurrentObservation(
        @SerializedName("display_location")
        val displayLocation: DisplayLocation,
        @SerializedName("temp_f")
        val tempF: Int,
        @SerializedName("temp_c")
        val tempC: Int,
        @SerializedName("weather")
        val weather: String
)