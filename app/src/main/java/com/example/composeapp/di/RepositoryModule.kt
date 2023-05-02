package com.example.composeapp.di
import com.example.composeapp.datasource.remote.service.NetworkServiceProvider
import com.example.composeapp.datasource.repository.MenuRepoImp
import com.example.core.repository.MenuRepo
import com.example.core.usecase.GetMenuList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMenuRepo(networkServiceProvider: NetworkServiceProvider): MenuRepo = MenuRepoImp(networkServiceProvider = networkServiceProvider, dispatcher = Dispatchers.IO)

    @Provides
    @Singleton
    fun provideGetMenuList(repo: MenuRepo): GetMenuList = GetMenuList(repo =repo )

}