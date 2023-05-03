package com.example.composeapp.presentation.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.entities.MenuItem
import com.example.core.usecase.GetMenuList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val getMenuList: GetMenuList) : ViewModel() {
    private val _state= MutableStateFlow<MenuViewStates>(MenuViewStates.Loading)
    val state get() =  _state.asStateFlow()
 fun fetchMenuList()=viewModelScope.launch {
    _state.emitAll( flow<MenuViewStates> { emit(MenuViewStates.Success(menu = getMenuList.invoke())) }
    .onStart { emit(MenuViewStates.Loading) }
    .catch { throwable -> emit(MenuViewStates.Error( throwable)) })
}
    init { fetchMenuList() }
    }