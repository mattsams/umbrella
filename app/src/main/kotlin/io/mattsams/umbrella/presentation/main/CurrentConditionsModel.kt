package io.mattsams.umbrella.presentation.main

data class CurrentConditionsModel(
        val location: String,
        val temperature: Int,
        val conditions: String
)