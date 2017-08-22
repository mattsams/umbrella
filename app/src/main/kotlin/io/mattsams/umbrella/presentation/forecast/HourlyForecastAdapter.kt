package io.mattsams.umbrella.presentation.forecast

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.mattsams.umbrella.R
import io.mattsams.umbrella.presentation.forecast.model.DailyForecastModel
import io.mattsams.umbrella.presentation.forecast.model.HourlyForecastModel

class HourlyForecastAdapter(context: Context, private val data: List<HourlyForecastModel>)
    : RecyclerView.Adapter<HourlyForecastViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: HourlyForecastViewHolder?, position: Int) {
        if (holder == null) return
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HourlyForecastViewHolder =
            HourlyForecastViewHolder(inflater.inflate(R.layout.view_hourly_forecast, parent, false))
}