package io.mattsams.umbrella.presentation.forecast

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.mattsams.umbrella.R
import io.mattsams.umbrella.binding.bindView
import io.mattsams.umbrella.data.api.IconProvider
import io.mattsams.umbrella.presentation.forecast.model.HourlyForecastModel
import io.mattsams.umbrella.presentation.forecast.model.IconHighlight
import io.mattsams.umbrella.toDegrees
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

class HourlyForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        val TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
    }

    private val timeView by bindView<TextView>(R.id.hourly_forecast_time)
    private val iconView by bindView<ImageView>(R.id.hourly_forecast_icon)
    private val temperatureView by bindView<TextView>(R.id.hourly_forecast_temperature)

    fun bind(forecast: HourlyForecastModel) {
        timeView.text = forecast.time.format(TIME_FORMATTER)
        temperatureView.text = forecast.temp.toDegrees()

        val color = ContextCompat.getColor(itemView.context, when (forecast.highlight) {
            IconHighlight.NONE -> android.R.color.primary_text_light
            IconHighlight.COOL -> R.color.cool
            IconHighlight.WARM -> R.color.warm
        })

        timeView.setTextColor(color)
        temperatureView.setTextColor(color)

        Picasso.with(itemView.context)
                .load(IconProvider.url(forecast.icon, forecast.highlight != IconHighlight.NONE))
                .into(iconView, object : Callback {
                    override fun onSuccess() {
                        iconView.setColorFilter(color)
                    }

                    override fun onError() {
                    }
                })
    }
}