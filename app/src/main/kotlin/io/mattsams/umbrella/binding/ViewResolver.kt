package io.mattsams.umbrella.binding

import java.lang.ref.WeakReference

class ViewResolver<R, out V : android.view.View>(
        receiver: R,
        private val id: Int,
        private val func: (R, Int) -> V
) {
    private val receiverRef = WeakReference(receiver)

    fun resolve() = receiverRef.get()?.let { func(it, id) }
            ?: throw RuntimeException("View receiver not available.")
}