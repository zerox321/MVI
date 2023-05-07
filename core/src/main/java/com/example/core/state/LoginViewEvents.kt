package com.example.core.state

sealed class LoginViewEvents  {
    data class Login(val phoneNumber:String,val password:String) :LoginViewEvents()
}