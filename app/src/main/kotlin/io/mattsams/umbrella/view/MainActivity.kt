package io.mattsams.umbrella.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.mattsams.umbrella.R
import io.mattsams.umbrella.binding.bindView
import io.mattsams.umbrella.view.conditions.ConditionsWidget

class MainActivity : AppCompatActivity() {
    private val conditionsWidget by bindView<ConditionsWidget>(R.id.conditions_widget)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(conditionsWidget.toolbar)
    }
}