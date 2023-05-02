package com.example.composeapp.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import com.example.composeapp.datasource.remote.OkHttpBuilder
import com.example.composeapp.datasource.remote.RetrofitBuilder
import com.example.composeapp.datasource.remote.service.NetworkServiceProvider
import com.example.composeapp.utility.ConnectionUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context) =
        ContextCompat.getSystemService(context, ConnectivityManager::class.java) as ConnectivityManager


    @Provides
    @Singleton
    fun provideConnectionUtil(manger: ConnectivityManager) = ConnectionUtil(manger)

    @Provides
    @Singleton
    fun provideOkHttpBuilder(connectionUtil: ConnectionUtil,@ApplicationContext context: Context) = OkHttpBuilder(connectionUtil=connectionUtil, context = context)
    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpBuilder: OkHttpBuilder): RetrofitBuilder = RetrofitBuilder( baseUrl = "https://raw.githubusercontent.com/zerox321/Restaurant/master/api/", okHttpBuilder =okHttpBuilder   )

    @Provides
    @Singleton
    fun provideNetworkServiceProvider(retrofitBuilder: RetrofitBuilder) = NetworkServiceProvider(retrofitBuilder=retrofitBuilder)


}