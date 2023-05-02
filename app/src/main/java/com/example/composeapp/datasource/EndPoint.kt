package com.example.composeapp.datasource

object EndPoint {
    const val HEADER_CACHE_CONTROL = "Cache-Control"
    const val HEADER_PRAGMA = "Pragma"
    const val timeOut =60L
    const val maxDays =7
    const val CACHE_SIZE: Long = (10 * 1024 * 1024).toLong()// 10 MB

}