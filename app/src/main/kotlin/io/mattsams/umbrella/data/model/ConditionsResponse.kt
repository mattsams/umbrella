package io.mattsams.umbrella.data.model

import com.google.gson.annotations.SerializedName

data class ConditionsResponse(
        @SerializedName("current_observation")
        val currentObservation: CurrentObservation
)