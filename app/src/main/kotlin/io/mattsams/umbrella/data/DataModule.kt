package io.mattsams.umbrella.data

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.byteunits.DecimalByteUnit
import dagger.Module
import dagger.Provides
import io.mattsams.umbrella.data.api.ApiModule
import io.mattsams.umbrella.data.api.WeatherUndergroundApi
import io.mattsams.umbrella.isNetworkAvailable
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = arrayOf(ApiModule::class))
class DataModule {
    companion object {
        const val CACHE_CONTROL = "Cache-Control"
        val CACHE_SIZE = DecimalByteUnit.MEGABYTES.toBytes(50)
    }

    @Provides
    @Singleton
    fun providesCache(context: Context): Cache? {
        return try {
            Cache(File(context.cacheDir, "http"), CACHE_SIZE)
        } catch (ex: Exception) {
            Timber.e(ex, "Unable to create cache.")
            null
        }
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(context: Context, cache: Cache?): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addNetworkInterceptor { chain ->
            val response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder().maxAge(1, TimeUnit.DAYS).build()
            response.newBuilder()
                    .removeHeader("Pragma")
                    .header(CACHE_CONTROL, cacheControl.toString()).build()
        }

        builder.addInterceptor(HttpLoggingInterceptor {
            Timber.i(it)
        }.apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        })

        builder.addInterceptor { chain ->
            var request = chain.request()
            if (!context.isNetworkAvailable()) {
                val cacheControl = CacheControl.Builder()
                        .maxStale(1, TimeUnit.DAYS)
                        .build()

                request = request.newBuilder()
                        .cacheControl(cacheControl).build()
            }

            chain.proceed(request)
        }

        return builder
                .cache(cache)
                .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient, gson: Gson, @Named("API_URL") baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Provides
    @Singleton
    fun providesWeatherUndergroundApi(retrofit: Retrofit): WeatherUndergroundApi =
            retrofit.create(WeatherUndergroundApi::class.java)
}