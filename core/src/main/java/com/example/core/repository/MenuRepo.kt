package com.example.core.repository

import com.example.core.entities.MenuItem

interface  MenuRepo {
    suspend fun fetchApi(): List<MenuItem>
}