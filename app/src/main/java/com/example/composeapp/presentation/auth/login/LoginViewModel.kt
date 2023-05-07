package com.example.composeapp.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.state.LoginViewEvents
import com.example.core.state.LoginViewStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
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

    private val _states= Channel<LoginViewStates>()
    val states get() =  _states.receiveAsFlow()
    private fun login(phoneValue:String,passwordValue:String)=viewModelScope.launch {
        _states.send(LoginViewStates.Loading)

        Timber.e("phoneValue $phoneValue , passwordValue $passwordValue")

        if(phoneValue.isEmpty()) {
            _states.send(LoginViewStates.EmptyPhoneNumber)
        return@launch
        }
        if(passwordValue.isEmpty()) {
            _states.send(LoginViewStates.EmptyPassword)
            return@launch
        }
        delay(4000)
        _states.send(LoginViewStates.LoginSuccess)

    }

    fun onEvent(event: LoginViewEvents){
        when(event){
            is LoginViewEvents.Login -> login(
                phoneValue = event.phoneNumber,
                passwordValue = event.password
            )
            else -> throw IllegalArgumentException("Unknown event: $event")
        }
    }

}