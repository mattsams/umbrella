package io.mattsams.umbrella

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private val app: UmbrellaApp) {
    @Provides
    @Singleton
    fun providesContext(): Context = app

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder()
                .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun providesPreferences(sharedPreferences: SharedPreferences): UmbrellaPreferences =
            UmbrellaPreferencesImpl(sharedPreferences)

    @Provides
    @Named("mainScheduler")
    @Singleton
    fun providesMainScheduler(): Scheduler = AndroidSchedulers.mainThread()
}