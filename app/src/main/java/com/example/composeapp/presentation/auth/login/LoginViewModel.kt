package com.example.composeapp.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor():ViewModel(){
    fun setPhoneNumber(phone: String) =viewModelScope.launch { _phoneNumber.emit(phone) }

    private val _phoneNumber= MutableStateFlow<String>("")
    val phoneNumber get() =  _phoneNumber.asStateFlow()
}