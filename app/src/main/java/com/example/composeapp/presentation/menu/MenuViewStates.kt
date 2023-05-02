package com.example.composeapp.presentation.menu

import com.example.core.entities.MenuItem


sealed class MenuViewStates{
    object Loading:MenuViewStates()
    data class Success(val menu: List<MenuItem>):MenuViewStates()
    data class Error(val error: Throwable):MenuViewStates()
}
