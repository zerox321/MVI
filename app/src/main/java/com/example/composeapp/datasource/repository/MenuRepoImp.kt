package com.example.composeapp.datasource.repository

import com.example.composeapp.datasource.remote.service.NetworkServiceProvider
import com.example.core.entities.MenuItem
import com.example.core.repository.MenuRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MenuRepoImp (private val networkServiceProvider: NetworkServiceProvider, private val dispatcher: CoroutineDispatcher): MenuRepo {
    override suspend fun fetchApi(): List<MenuItem> = withContext(dispatcher){ networkServiceProvider.authService.fetchApi()}
}