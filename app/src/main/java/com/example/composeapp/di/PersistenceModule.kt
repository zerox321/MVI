package com.example.composeapp.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import com.example.composeapp.BuildConfig.APPLICATION_ID
import com.example.composeapp.datasource.local.PreferenceBuilder
import com.example.composeapp.datasource.local.PreferenceController
import com.example.composeapp.utility.ConnectionUtil
import com.example.core.BuildConfig.BUILD_TYPE
import com.example.core.BuildConfig.DEBUG
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun providePreferenceBuilder(@ApplicationContext appContext: Context): PreferenceBuilder =
        PreferenceBuilder(context = appContext, name = "$APPLICATION_ID.$BUILD_TYPE", isDebug =DEBUG )

    @Provides
    @Singleton
    fun providePreferenceController(builder: PreferenceBuilder): PreferenceController =
        PreferenceController(builder=builder, dispatcher = Dispatchers.IO )


}