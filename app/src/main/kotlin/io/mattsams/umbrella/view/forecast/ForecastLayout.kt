package io.mattsams.umbrella.view.forecast

import android.content.Context
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout
import io.mattsams.umbrella.R
import io.mattsams.umbrella.binding.bindView
import io.mattsams.umbrella.injector
import io.mattsams.umbrella.presentation.forecast.DailyForecastAdapter
import io.mattsams.umbrella.presentation.forecast.ForecastPresenter
import io.mattsams.umbrella.presentation.forecast.ForecastView
import io.mattsams.umbrella.presentation.forecast.model.DailyForecastModel
import javax.inject.Inject

class ForecastLayout(context: Context, attrs: AttributeSet)
    : LinearLayout(context, attrs), ForecastView {

    private val recyclerView by bindView<RecyclerView>(R.id.forecast_list)

    @field:[Inject] lateinit var presenter: ForecastPresenter

    init {
        if (!isInEditMode) injector().inject(this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (isInEditMode) return
        recyclerView.layoutManager = LinearLayoutManager(context)
        presenter.attach(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (isInEditMode) return
        presenter.detach(this)
    }

    override fun loadForecast(forecast: List<DailyForecastModel>) {
        recyclerView.adapter = DailyForecastAdapter(context, forecast)
    }
}