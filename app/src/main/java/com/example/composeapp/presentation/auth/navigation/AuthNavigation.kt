package com.example.composeapp.presentation.auth.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.presentation.auth.forgetPassword.ForgetPasswordView
import com.example.composeapp.presentation.auth.login.LoginView
import com.example.composeapp.presentation.auth.onBoarding.OnBoardingScreen
import com.example.composeapp.presentation.auth.register.RegisterView

sealed class AuthNavScreen(val route: String) {
    object OnBoardingScreen : AuthNavScreen("OnBoarding")
    object LoginScreen : AuthNavScreen("LoginScreen")
    object RegisterScreen : AuthNavScreen("RegisterScreen")
    object ForgetPasswordScreen : AuthNavScreen("ForgetPasswordScreen")
}

@Composable
fun AuthNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AuthNavScreen.OnBoardingScreen.route
    ) {
        composable(AuthNavScreen.OnBoardingScreen.route) { OnBoardingScreen(navController = navController) }
        composable(AuthNavScreen.LoginScreen.route) { LoginView(navController = navController) }
        composable(AuthNavScreen.RegisterScreen.route) { RegisterView(navController = navController) }
        composable(AuthNavScreen.ForgetPasswordScreen.route) { ForgetPasswordView(navController = navController) }
    }
}