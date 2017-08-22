package io.mattsams.umbrella.presentation.forecast

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.mattsams.umbrella.R
import io.mattsams.umbrella.presentation.forecast.model.DailyForecastModel

class DailyForecastAdapter(context: Context, private val data: List<DailyForecastModel>)
    : RecyclerView.Adapter<DailyForecastViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: DailyForecastViewHolder?, position: Int) {
        if (holder == null) return
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DailyForecastViewHolder {
        val vh = DailyForecastViewHolder(inflater.inflate(R.layout.view_daily_forecast, parent, false))
        vh.init()
        return vh
    }
}