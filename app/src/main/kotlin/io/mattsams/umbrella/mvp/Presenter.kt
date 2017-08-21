package io.mattsams.umbrella.mvp

interface Presenter<in V : MvpView> {
    fun attach(view: V)

    fun detach(view: V)
}