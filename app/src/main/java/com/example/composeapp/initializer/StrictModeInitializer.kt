@file:Suppress("unused")
package com.example.composeapp.initializer
import android.content.Context
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import androidx.startup.Initializer
import com.example.composeapp.BuildConfig
import timber.log.Timber


class StrictModeInitializer : Initializer<Unit> {
    private val tag="StrictModeInitializer"

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                    .build()
            )
            Timber.tag(tag).e("StrictMode is initialized.")
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}