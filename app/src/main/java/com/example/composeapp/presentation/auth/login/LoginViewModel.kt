package com.example.composeapp.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.state.LoginViewStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor():ViewModel(){

    private val _phoneNumber= MutableStateFlow<String>("")
    val phoneNumber get() =  _phoneNumber.asStateFlow()
    fun setPhoneNumber(phone: String) =viewModelScope.launch { _phoneNumber.emit(phone) }


    private val _password= MutableStateFlow<String>("")
    val password get() =  _password.asStateFlow()
    fun setPassword(password: String) =viewModelScope.launch { _password.emit(password) }

    private val _states= MutableStateFlow<LoginViewStates>(LoginViewStates.Idle)
    val states get() =  _states.asStateFlow()
    fun login()=viewModelScope.launch {
        val phoneValue=_phoneNumber.value
        val passwordValue=_password.value

        Timber.e("phoneValue $phoneValue , passwordValue $passwordValue")

        if(phoneValue.isEmpty()) {
            _states.emit(LoginViewStates.EmptyPhoneNumber)
        return@launch
        }
        if(passwordValue.isEmpty()) {
            _states.emit(LoginViewStates.EmptyPassword)
            return@launch
        }
        _states.emit(LoginViewStates.Loading)
        delay(4000)
        _states.emit(LoginViewStates.LoginSuccess)

    }

}