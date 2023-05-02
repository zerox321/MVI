package com.example.composeapp.datasource.remote.interceptor

import com.example.composeapp.datasource.EndPoint.HEADER_CACHE_CONTROL
import com.example.composeapp.datasource.EndPoint.HEADER_PRAGMA
import com.example.composeapp.datasource.EndPoint.maxDays
import com.example.composeapp.utility.ConnectionUtil
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class OfflineInterceptor @Inject constructor(
    private val connectionUtil: ConnectionUtil
) : Interceptor {
    private val isNetworkAvailable get() = connectionUtil.isNetworkInterfaceAvailable()
    private val cacheControl by lazy{CacheControl.Builder().maxStale(maxDays, TimeUnit.DAYS).build()}

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!isNetworkAvailable) {
            request = request.newBuilder().addHeader("Content-Type", "application/json")
                .removeHeader(HEADER_PRAGMA).removeHeader(HEADER_CACHE_CONTROL)
                .cacheControl(cacheControl).build()
        }
        return chain.proceed(request)
    }

}