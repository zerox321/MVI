package com.example.composeapp.presentation.menu

import android.graphics.Color.parseColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composeapp.R
import com.example.composeapp.presentation.NavScreen
import com.example.composeapp.presentation.ui.widget.NetworkImage
import com.example.composeapp.presentation.ui.widget.RatingBar
import com.example.composeapp.presentation.ui.widget.TextWithMinLines
import com.example.core.entities.MenuItem
import timber.log.Timber

@Composable
fun MenuView(navController: NavController) {
    val viewModel: MenuViewModel = hiltViewModel()
    LaunchedEffect(true) {
        viewModel.onEvent(event = MenuViewEvents.Refresh)
    }

    val state by viewModel.state.collectAsState()
    ConstraintLayout(
    ) {
        val (list) = createRefs()

        LazyRow(modifier = Modifier
            .constrainAs(list) {
                top.linkTo(parent.top, 30.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

            }) {
            items(
                items = (state as? MenuViewStates.Success)?.menu ?: emptyList(),
                itemContent = { item ->
                    Spacer(modifier = Modifier.width(8.dp))
                    RenderMenuItem(item = item, navController = navController)
                    Spacer(modifier = Modifier.width(8.dp))
                })

        }
    }
//    }
}


@Composable
fun RenderMenuItem(navController: NavController, item: MenuItem) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()

            .background(
                Color(parseColor(item.color ?: "#FF0000")),
                shape = RoundedCornerShape(16.dp)
            )

            .padding(8.dp)
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "Menu",
                    item
                )
                navController.navigate(NavScreen.MenuDetails.route)
            }

    ) {
        val (image, price, title, ratting, addButton) = createRefs()
        NetworkImage(
            url = item.thumbnail,
            modifier = Modifier
                .clip(RoundedCornerShape(16))
                .constrainAs(image) {
                    top.linkTo(parent.top, 16.dp)
                    start.linkTo(parent.start, 30.dp)
                    end.linkTo(parent.end, 30.dp)
                }
                .size(130.dp)


        )

        Text(
            text = "${item.price} $",
            style = TextStyle(
                textAlign = TextAlign.Center,
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            modifier = Modifier
                .constrainAs(price) {
                    top.linkTo(image.bottom, 16.dp)
                    start.linkTo(parent.start, 8.dp)
                    end.linkTo(parent.end, 4.dp)
                    width = Dimension.fillToConstraints

                }

        )
        TextWithMinLines(
            text = "${item.name}",
            style = TextStyle(textAlign = TextAlign.Center, color = White, fontSize = 16.sp),
            minLines = 3,
            softWrap = true,
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(price.bottom, 8.dp)
                    width = Dimension.fillToConstraints

                }

        )


        RatingBar(rating = item.rate ?: 0f, numStars = 5, modifier = Modifier
            .constrainAs(ratting) {
                top.linkTo(title.bottom, 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })


        Image(painter = painterResource(R.drawable.add_icon), contentDescription = "",

            modifier = Modifier
                .clickable {
                    Timber.e("Add Button CLick")
                }
                .constrainAs(addButton) {
                    top.linkTo(ratting.bottom, 16.dp)
                    bottom.linkTo(parent.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }

        )

    }
}







