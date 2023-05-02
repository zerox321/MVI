@file:Suppress("unused")
package com.example.composeapp.initializer
import android.content.Context
import androidx.startup.Initializer
import com.example.composeapp.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {
    private val tag="TimberInitializer"

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.tag(tag).e("Timber is initialized.")
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}