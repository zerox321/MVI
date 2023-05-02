package com.example.composeapp.utility
import android.net.ConnectivityManager

@Suppress("DEPRECATION")
class ConnectionUtil(private val manger: ConnectivityManager) {
    fun isNetworkInterfaceAvailable(): Boolean {
        val activeNetwork = manger.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }}