package com.example.composeapp.presentation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.presentation.menu.MenuView
import com.example.composeapp.presentation.menuDetail.MenuDetailScreen

sealed class NavScreen(val route: String) {
    object MenuScreen : NavScreen("Menu")
    object MenuDetails : NavScreen("MenuDetails") {
        const val routeWithArgument: String = "MenuDetails/{menuId}"
        const val argument0: String = "menuId"
    }


}
@Composable
fun MenuNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavScreen.MenuScreen.route
    ) {
        composable(NavScreen.MenuScreen.route) { MenuView(navController = navController) }
        composable(
            route = NavScreen.MenuDetails.route) {navBackStackEntry ->
            val menuId = navBackStackEntry.arguments?.getString("menuId")
            MenuDetailScreen(navController = navController)
        }

    }
}