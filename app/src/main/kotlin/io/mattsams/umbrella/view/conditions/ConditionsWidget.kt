package io.mattsams.umbrella.view.conditions

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.ColorInt
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import io.mattsams.umbrella.R
import io.mattsams.umbrella.binding.bindView
import io.mattsams.umbrella.injector
import io.mattsams.umbrella.presentation.conditions.ConditionsPresenter
import io.mattsams.umbrella.presentation.conditions.ConditionsView
import javax.inject.Inject

class ConditionsWidget(context: Context, attrs: AttributeSet) : AppBarLayout(context, attrs), ConditionsView {
    companion object {
        const val COLOR_ANIMATION_TIME = 1000L
    }

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

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        return CustomSaveState(superState).apply {
            backgroundColor = currentBackgroundColor()
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val customState = state as CustomSaveState
        super.onRestoreInstanceState(customState.superState)
        if (customState.backgroundColor != 0) updateViewColors(customState.backgroundColor)
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

    private fun currentBackgroundColor(): Int {
        val bg = background
        return if (bg is ColorDrawable) return bg.color else 0
    }

    private fun updateViewColors(color: Int) {
        setBackgroundColor(color)
        toolbar.setBackgroundColor(color)
    }

    override fun updateColor(colorId: Int, animated: Boolean) {
        val colorFrom = currentBackgroundColor()
        val colorTo = ContextCompat.getColor(context, colorId)
        if (colorFrom == colorTo) return

        if (animated) {
            val animator = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
            animator.addUpdateListener { updateViewColors(it.animatedValue as Int) }
            animator.duration = COLOR_ANIMATION_TIME
            animator.start()
        } else {
            updateViewColors(colorTo)
        }
    }

    class CustomSaveState : BaseSavedState {
        constructor(superState: Parcelable) : super(superState)

        constructor(parcel: Parcel) : super(parcel) {
            backgroundColor = parcel.readInt()
        }

        var backgroundColor: Int = 0

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            super.writeToParcel(parcel, flags)
            parcel.writeInt(backgroundColor)
        }

        override fun describeContents(): Int = 0

        companion object CREATOR : Parcelable.Creator<CustomSaveState> {
            override fun createFromParcel(parcel: Parcel): CustomSaveState = CustomSaveState(parcel)

            override fun newArray(size: Int): Array<CustomSaveState?> = arrayOfNulls(size)
        }

    }
}