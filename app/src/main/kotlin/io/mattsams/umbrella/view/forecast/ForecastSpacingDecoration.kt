package io.mattsams.umbrella.view.forecast

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class ForecastSpacingDecoration(private val space: Int, private val spanCount: Int)
    : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        if (parent == null || outRect == null) return
        if (parent.getChildLayoutPosition(view) >= spanCount) outRect.top = space
        else outRect.top = 0
    }
}