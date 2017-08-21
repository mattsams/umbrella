package io.mattsams.umbrella.presentation.main

import io.mattsams.umbrella.mvp.SimplePresenter
import io.reactivex.Scheduler

class MainPresenterImpl(private val interactor: MainInteractor, private val scheduler: Scheduler)
    : SimplePresenter<MainView>(), MainPresenter {


}