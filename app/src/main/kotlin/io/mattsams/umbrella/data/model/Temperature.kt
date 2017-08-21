package io.mattsams.umbrella.data.model

import com.google.gson.annotations.SerializedName

/**
 * Encapsulates the 'temp' portion of 'hourly_forecast'.
 */
data class Temperature(
        @SerializedName("english")
        val english: Int,
        @SerializedName("metric")
        val metric: Int
)