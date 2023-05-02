package com.example.composeapp.datasource.remote.service

import com.example.core.entities.MenuItem
import retrofit2.http.GET

interface AuthService {

    @GET(value = "api") suspend fun fetchApi(): List<MenuItem>
}