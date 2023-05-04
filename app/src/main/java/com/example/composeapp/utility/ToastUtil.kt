package com.example.composeapp.utility
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

@Suppress("DEPRECATION")
object ToastUtil {
    fun Context.showError(message:String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }}