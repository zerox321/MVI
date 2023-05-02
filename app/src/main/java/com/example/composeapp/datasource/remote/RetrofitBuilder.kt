package com.example.composeapp.datasource.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitBuilder @Inject constructor(private val okHttpBuilder: OkHttpBuilder, private val baseUrl:String){
    private val gsonConverterFactory :GsonConverterFactory by lazy { GsonConverterFactory.create() }
    val retrofit :Retrofit by lazy { Retrofit.Builder().client(okHttpBuilder.okHttpClient).baseUrl(baseUrl).addConverterFactory(gsonConverterFactory).build() }
}
