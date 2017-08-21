package io.mattsams.umbrella.view.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.mattsams.umbrella.R
import io.mattsams.umbrella.binding.bindView
import io.mattsams.umbrella.presentation.main.MainView
import io.mattsams.umbrella.presentation.main.model.CurrentConditionsModel
import io.mattsams.umbrella.presentation.main.model.DailyForecastModel
import io.mattsams.umbrella.view.main.conditions.ConditionsWidget

class MainActivity : AppCompatActivity(), MainView {
    private val conditionsWidget by bindView<ConditionsWidget>(R.id.conditions_widget)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(conditionsWidget)
        setTitle(R.string.app_name)
    }

    override fun loadCurrentConditions(currentConditions: CurrentConditionsModel) {
    }

    override fun loadForecast(forecast: List<DailyForecastModel>) {
    }
}