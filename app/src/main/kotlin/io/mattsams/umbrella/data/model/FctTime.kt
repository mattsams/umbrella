package io.mattsams.umbrella.data.model

import com.google.gson.annotations.SerializedName

/**
 * Encapsulates the 'FCTTIME' portion of 'hourly_forecast'.
 */
data class FctTime(
        @SerializedName("hour")
        val hour: Int,
        @SerializedName("min")
        val min: Int,
        @SerializedName("mon")
        val mon: Int,
        @SerializedName("mday")
        val mday: Int,
        @SerializedName("year")
        val year: Int,
        @SerializedName("ampm")
        val ampm: String
)