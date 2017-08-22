package io.mattsams.umbrella.presentation.forecast

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import io.mattsams.umbrella.R
import io.mattsams.umbrella.binding.bindView
import io.mattsams.umbrella.dpToPx
import io.mattsams.umbrella.presentation.forecast.model.DailyForecastModel
import io.mattsams.umbrella.view.forecast.ForecastSpacingDecoration
import org.threeten.bp.LocalDate
import org.threeten.bp.format.TextStyle
import java.util.*

class DailyForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        const val FORECAST_COLUMNS = 4
    }

    private val titleView by bindView<TextView>(R.id.daily_forecast_title)
    private val hourlyForecastView by bindView<RecyclerView>(R.id.daily_forecast_hourly_list)

    fun init() {
        hourlyForecastView.layoutManager = GridLayoutManager(
                itemView.context,
                FORECAST_COLUMNS,
                GridLayoutManager.VERTICAL, false
        )

        hourlyForecastView.addItemDecoration(ForecastSpacingDecoration(
                itemView.context.dpToPx(12.0),
                FORECAST_COLUMNS
        ))
    }

    fun bind(forecast: DailyForecastModel) {
        val today = LocalDate.now()
        titleView.text = when (forecast.date.dayOfMonth - today.dayOfMonth) {
            0 -> itemView.context.getString(R.string.today)
            1 -> itemView.context.getString(R.string.tomorrow)
            else -> forecast.date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        }

        hourlyForecastView.adapter = HourlyForecastAdapter(itemView.context, forecast.hourly)
    }
}