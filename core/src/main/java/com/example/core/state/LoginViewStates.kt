package com.example.core.state

sealed class LoginViewStates  {
    object Idle :LoginViewStates()
    object Loading :LoginViewStates()
    object EmptyPhoneNumber :LoginViewStates()
    object EmptyPassword :LoginViewStates()
    object LoginSuccess :LoginViewStates()
}