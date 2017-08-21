package io.mattsams.umbrella

import android.content.SharedPreferences

class UmbrellaPreferences(val prefs: SharedPreferences) {
    companion object {
        const val POSTAL_CODE = "postal_code"
        const val UNITS = "units"

        const val DEFAULT_POSTAL_CODE = "40601" // Frankfort, KY
        val DEFAULT_UNITS = Units.IMPERIAL
    }

    var postalCode: String?
        get() = prefs.getString(POSTAL_CODE, DEFAULT_POSTAL_CODE)
        set(value) {
            prefs.edit().putString(POSTAL_CODE, value).apply()
        }

    var units: Units
        get() = Units.valueOf(prefs.getString(UNITS, DEFAULT_UNITS.toString()))
        set(value) {
            prefs.edit().putString(UNITS, value.toString()).apply()
        }
}