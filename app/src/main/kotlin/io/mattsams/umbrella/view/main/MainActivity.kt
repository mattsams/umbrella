package io.mattsams.umbrella.view.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import io.mattsams.umbrella.R
import io.mattsams.umbrella.UmbrellaApp
import io.mattsams.umbrella.presentation.main.MainView
import io.mattsams.umbrella.presentation.main.model.CurrentConditionsModel
import io.mattsams.umbrella.presentation.main.model.DailyForecastModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {
    @field:[Inject] lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        UmbrellaApp.graph.inject(this)
    }

    override fun loadCurrentConditions(currentConditions: CurrentConditionsModel) {
    }

    override fun loadForecast(forecast: List<DailyForecastModel>) {
    }
}