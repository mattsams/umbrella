package io.mattsams.umbrella.view

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.PreferenceFragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import io.mattsams.umbrella.R
import io.mattsams.umbrella.UmbrellaPreferences
import io.mattsams.umbrella.binding.bindView
import io.mattsams.umbrella.injector
import javax.inject.Inject

class UmbrellaPreferencesActivity : AppCompatActivity() {
    private val toolbar by bindView<Toolbar>(R.id.preferences_toolbar)

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        fragmentManager.beginTransaction()
                .replace(R.id.preferences_content, UmbrellaPreferencesFragment())
                .commit()
    }

    class UmbrellaPreferencesFragment : PreferenceFragment() {
        @field:[Inject] lateinit var prefs: SharedPreferences
        private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            updateSummary(key)
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            activity.injector().inject(this)
            addPreferencesFromResource(R.xml.preferences)
            updateSummary(UmbrellaPreferences.POSTAL_CODE)
        }

        override fun onStart() {
            super.onStart()
            prefs.registerOnSharedPreferenceChangeListener(listener)
        }

        override fun onStop() {
            super.onStop()
            prefs.unregisterOnSharedPreferenceChangeListener(listener)
        }

        private fun updateSummary(key: String?) {
            if (key.isNullOrEmpty()) return
            when (key) {
                UmbrellaPreferences.POSTAL_CODE -> {
                    val pref = preferenceScreen.findPreference(key) as EditTextPreference
                    pref.summary = pref.text
                }
            }
        }
    }
}