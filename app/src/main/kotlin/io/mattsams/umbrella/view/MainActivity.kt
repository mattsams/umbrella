package io.mattsams.umbrella.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import io.mattsams.umbrella.BuildConfig
import io.mattsams.umbrella.R
import io.mattsams.umbrella.binding.bindView
import io.mattsams.umbrella.view.conditions.ConditionsWidget
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val conditionsWidget by bindView<ConditionsWidget>(R.id.conditions_widget)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_settings -> TODO()
            else -> return false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(conditionsWidget.toolbar)
        Timber.i(BuildConfig.API_KEY)
    }
}