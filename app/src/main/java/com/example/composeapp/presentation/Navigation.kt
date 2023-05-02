package com.example.composeapp.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.presentation.menuDetail.MenuDetailScreen
import com.example.composeapp.presentation.menuDetail.MenuDetailScreenTag
import com.example.composeapp.presentation.menu.MenuScreen
import com.example.composeapp.presentation.menu.MenuScreenTag
import com.example.composeapp.presentation.menu.MenuViewModel

@Composable
fun MenuNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MenuScreenTag
    ) {
        composable(MenuScreenTag) {
            val viewModel: MenuViewModel = hiltViewModel()
            MenuScreen(navController = navController,viewModel=viewModel)
        }
        composable(MenuDetailScreenTag) {
            MenuDetailScreen(navController = navController)
        }


    }
}