package com.example.composeapp.presentation

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
    class MainViewModel @Inject constructor(private val getMenuList: GetMenuList) : ViewModel() {
private val _state= MutableStateFlow<List<MenuItem>>(emptyList())
val state get() =  _state.asStateFlow()
fun fetchMenuList()=viewModelScope.launch {
    _state.emitAll(flow {emit( getMenuList.invoke() )}.onStart { emit(emptyList()) }.catch { t->Timber.e(t) })

}
    init {
        fetchMenuList()
    }
    }