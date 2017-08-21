package io.mattsams.umbrella.view.conditions

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.widget.TextView
import io.mattsams.umbrella.R
import io.mattsams.umbrella.binding.bindView
import io.mattsams.umbrella.injector
import io.mattsams.umbrella.presentation.conditions.ConditionsPresenter
import io.mattsams.umbrella.presentation.conditions.ConditionsView
import javax.inject.Inject

class ConditionsWidget(context: Context, attrs: AttributeSet) : AppBarLayout(context, attrs), ConditionsView {
    private val temperatureView by bindView<TextView>(R.id.conditions_widget_temperature)
    private val conditionsView by bindView<TextView>(R.id.conditions_widget_conditions)

    val toolbar: Toolbar by bindView(R.id.conditions_widget_toolbar)

    @field:[Inject] lateinit var presenter: ConditionsPresenter

    init {
        if (!isInEditMode) injector().inject(this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (isInEditMode) return
        presenter.attach(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (isInEditMode) return
        presenter.detach(this)
    }

    override fun setLocation(location: String) {
        toolbar.title = location
    }

    override fun setTemperature(temperature: String) {
        temperatureView.text = temperature
    }

    override fun setConditions(conditions: String) {
        conditionsView.text = conditions
    }

    override fun updateColor(colorId: Int) {
        val bg = background
        if (bg is ColorDrawable) {
            val colorFrom = bg.color
            val colorTo = ContextCompat.getColor(context, colorId)
            if (colorFrom == colorTo) return

            ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo).apply {
                addUpdateListener {
                    val color = it.animatedValue as Int
                    setBackgroundColor(color)
                    toolbar.setBackgroundColor(color)
                }

                duration = 1000
                start()
            }
        }
    }
}