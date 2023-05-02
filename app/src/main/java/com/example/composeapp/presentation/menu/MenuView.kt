package com.example.composeapp.presentation.menu

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.composeapp.presentation.menuDetail.MenuDetailScreenTag

const val MenuScreenTag="MenuScreenTag"
@Composable
fun MenuScreen(navController: NavController,viewModel: MenuViewModel) {

    val state  by viewModel.state.collectAsState()
when (state){
    is MenuViewStates.Error -> Unit
    MenuViewStates.Loading -> Unit
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
                            navController.navigate(MenuDetailScreenTag)
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




