package io.mattsams.umbrella

interface UmbrellaPreferences {
    companion object {
        const val POSTAL_CODE = "postal_code"
        const val UNITS = "units"
    }

    var postalCode: String?
    var units: Units
}