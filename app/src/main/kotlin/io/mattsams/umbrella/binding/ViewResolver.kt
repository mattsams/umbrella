package io.mattsams.umbrella.binding

import android.support.annotation.IdRes
import android.view.View
import java.lang.ref.WeakReference

class ViewResolver<R, out V : View>(
        receiver: R,
        @IdRes private val id: Int,
        private val func: (R, Int) -> V
) {
    private val receiverRef = WeakReference(receiver)

    fun resolve(): V {
        return receiverRef.get()?.let { func(it, id) }
                ?: throw RuntimeException("View receiver not available.")
    }
}