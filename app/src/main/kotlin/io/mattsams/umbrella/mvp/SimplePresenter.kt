package io.mattsams.umbrella.mvp

import java.lang.ref.WeakReference

abstract class SimplePresenter<V : MvpView> : Presenter<V> {
    private var viewRef: WeakReference<V>? = null

    override fun attach(view: V) {
        viewRef = WeakReference(view)
    }

    override fun detach(view: V) {
        viewRef?.clear()
    }

    protected fun view(): V? = viewRef?.get()
}