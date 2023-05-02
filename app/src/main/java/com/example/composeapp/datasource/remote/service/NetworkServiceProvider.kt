package com.example.composeapp.datasource.remote.service

import com.example.composeapp.datasource.remote.RetrofitBuilder
import javax.inject.Inject


class NetworkServiceProvider @Inject constructor(private val retrofitBuilder: RetrofitBuilder){

    val authService: AuthService by lazy { retrofitBuilder. retrofit.create(AuthService::class.java) }


}