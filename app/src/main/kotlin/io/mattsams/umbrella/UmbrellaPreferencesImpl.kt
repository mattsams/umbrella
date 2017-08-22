package io.mattsams.umbrella

import android.content.SharedPreferences

class UmbrellaPreferencesImpl(private val prefs: SharedPreferences) : UmbrellaPreferences {
    companion object {
        const val DEFAULT_POSTAL_CODE = "40601" // Frankfort, KY
        val DEFAULT_UNITS = Units.IMPERIAL
    }

    override var postalCode: String?
        get() {
            val postalCode = prefs.getString(UmbrellaPreferences.POSTAL_CODE, DEFAULT_POSTAL_CODE)
            return if (postalCode.isNullOrEmpty()) DEFAULT_POSTAL_CODE
            else postalCode
        }
        set(value) {
            prefs.edit().putString(UmbrellaPreferences.POSTAL_CODE, value).apply()
        }

    override var units: Units
        get() = Units.valueOf(prefs.getString(UmbrellaPreferences.UNITS, DEFAULT_UNITS.toString()))
        set(value) {
            prefs.edit().putString(UmbrellaPreferences.UNITS, value.toString()).apply()
        }
}