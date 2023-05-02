package com.example.composeapp.datasource.remote

import android.content.Context
import com.example.composeapp.datasource.EndPoint
import com.example.composeapp.datasource.remote.interceptor.OfflineInterceptor
import com.example.composeapp.utility.ConnectionUtil
import com.example.core.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class OkHttpBuilder @Inject constructor( private val context: Context,connectionUtil: ConnectionUtil){
    private val cache by lazy { Cache(context.cacheDir, EndPoint.CACHE_SIZE) }


   private val loggingInterceptor by lazy {
           HttpLoggingInterceptor().apply {
               level =
                   if (BuildConfig.DEBUG)
                       HttpLoggingInterceptor.Level.BODY
                   else
                       HttpLoggingInterceptor.Level.NONE
           }
   }

 private val offlineInterceptor by lazy { OfflineInterceptor(connectionUtil = connectionUtil) }
     val okHttpClient by lazy {
        OkHttpClient.Builder().apply {
            connectTimeout(EndPoint.timeOut, TimeUnit.SECONDS)
            readTimeout(EndPoint.timeOut, TimeUnit.SECONDS)
            writeTimeout(EndPoint.timeOut, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
            addInterceptor(offlineInterceptor)
            cache(cache)
        }.build()
    }
}