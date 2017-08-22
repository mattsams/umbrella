package io.mattsams.umbrella.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import io.mattsams.umbrella.*
import io.mattsams.umbrella.binding.bindView
import io.mattsams.umbrella.view.conditions.ConditionsWidget
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {
    private val mainLayout by bindView<CoordinatorLayout>(R.id.main_layout)
    private val conditionsWidget by bindView<ConditionsWidget>(R.id.conditions_widget)
    private var snack: Snackbar? = null

    @field:[Inject]
    @field:[Named("mainScheduler")]
    lateinit var scheduler: Scheduler

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_settings -> {
                showPreferences()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(conditionsWidget.toolbar)

        injector().inject(this)

        EventBus.listen<InvalidData>()
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe({
                    invalidData(it.message)
                }, {
                    Timber.e(it)
                })
    }

    private fun invalidData(message: String) {
        if (snack?.isShownOrQueued == true) return
        snack = Snackbar.make(mainLayout, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok) {
                    showPreferences()
                }
        snack?.show()
    }

    private fun showPreferences() {
        startActivity(Intent(this, UmbrellaPreferencesActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        EventBus.emit(RefreshData())
    }
}