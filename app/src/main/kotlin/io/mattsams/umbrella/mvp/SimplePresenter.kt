package io.mattsams.umbrella.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

abstract class SimplePresenter<V : MvpView> : Presenter<V> {
    private var viewRef: WeakReference<V>? = null
    private val disposables = CompositeDisposable()

    override fun attach(view: V) {
        viewRef = WeakReference(view)
    }

    override fun detach(view: V) {
        viewRef?.clear()
        disposables.clear()
    }

    protected fun view(): V? = viewRef?.get()

    protected fun rx(disposable: Disposable) {
        disposables.add(disposable)
    }
}