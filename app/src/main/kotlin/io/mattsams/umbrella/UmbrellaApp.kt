package io.mattsams.umbrella

import android.app.Application
import android.os.Build
import android.support.v7.app.AppCompatDelegate
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

class UmbrellaApp : Application() {
    companion object {
        val graph: AppComponent by lazy {
            DaggerAppComponent.builder().build()
        }
    }

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }
}