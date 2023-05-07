package com.example.composeapp.presentation.menu

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composeapp.presentation.NavScreen
import com.example.composeapp.presentation.ui.widget.LoadingView
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MenuView(navController: NavController) {
    val viewModel: MenuViewModel = hiltViewModel()
    val state  by viewModel.state.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state is MenuViewStates.Loading)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = viewModel::fetchMenuList,
        modifier = Modifier.fillMaxSize()
    ) {
        when (state){
            is MenuViewStates.Error -> Unit
            MenuViewStates.Loading -> LoadingView()
            is MenuViewStates.Success -> {
                val menuList= (state as MenuViewStates.Success).menu
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    items(items = menuList, itemContent = { item ->
                        ConstraintLayout(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            val (text1, text2, button) = createRefs()

                            Text(
                                text = item.name?:"",
                                modifier = Modifier
                                    .constrainAs(text1) {
                                        top.linkTo(parent.top, margin = 16.dp)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                    }
                                    .fillMaxWidth(0.9f),
                            )

                            Text(
                                text = item.price.toString(),
                                modifier = Modifier
                                    .constrainAs(text2) {
                                        top.linkTo(text1.bottom, margin = 16.dp)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                    }
                                    .fillMaxWidth(0.9f),
                            )

                            Button(
                                onClick = {
                                    navController.currentBackStackEntry?.savedStateHandle?.set("Menu",item)
                                    navController.navigate(NavScreen.MenuDetails.route)
                                },
                                modifier = Modifier
                                    .constrainAs(button) {
                                        top.linkTo(text2.bottom, margin = 16.dp)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                    }
                                    .fillMaxWidth(0.9f)
                            ) {
                                Text(text = "Button")
                            }
                        }

                    })}}
        }
    }

}






