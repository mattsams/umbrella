package io.mattsams.umbrella.view.main.conditions

import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.widget.TextView
import io.mattsams.umbrella.R
import io.mattsams.umbrella.binding.bindView
import io.mattsams.umbrella.injector
import io.mattsams.umbrella.presentation.main.conditions.ConditionsPresenter
import io.mattsams.umbrella.presentation.main.conditions.ConditionsView
import javax.inject.Inject

class ConditionsWidget(context: Context, attrs: AttributeSet) : Toolbar(context, attrs), ConditionsView {
    private val temperatureView by bindView<TextView>(R.id.current_conditions_temperature)
    private val conditionsView by bindView<TextView>(R.id.current_conditions_conditions)

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

    override fun setTemperature(temperature: String) {
        temperatureView.text = temperature
    }

    override fun setConditions(conditions: String) {
        conditionsView.text = conditions
    }

    override fun updateColor(colorId: Int) {
        TODO()
    }
}