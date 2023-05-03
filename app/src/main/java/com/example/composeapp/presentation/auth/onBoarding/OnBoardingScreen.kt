package com.example.composeapp.presentation.auth.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composeapp.R
import com.example.composeapp.presentation.auth.navigation.AuthNavScreen
import com.example.composeapp.presentation.menu.MenuViewModel
import com.example.composeapp.presentation.ui.theme.Blue
import com.example.composeapp.presentation.ui.theme.Gray
import com.example.composeapp.presentation.ui.widget.PageIndicator
import com.example.composeapp.presentation.ui.widget.Pager
import timber.log.Timber

@Composable
fun OnBoardingScreen (navController: NavController){

    val viewModel: OnBoardingViewModel = hiltViewModel()
    val selectedIndex  by viewModel.selectedIndex.collectAsState()

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (pager,indicator ,next,skip) = createRefs()

        Pager(
        items = viewModel.items,
        modifier = Modifier.constrainAs(pager) {
            top.linkTo(parent.top)
            bottom.linkTo(indicator.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        },
        initialIndex = selectedIndex,
        onItemSelect = {index-> viewModel.setSelectedIndex(value=index) },
        contentFactory = { item -> PageItem(item=item) }
    )
        PageIndicator(selectedPage = selectedIndex,numberOfPages=viewModel.itemsCount ,
            modifier = Modifier.constrainAs(indicator) {
                top.linkTo(next.top)
                bottom.linkTo(next.bottom)
                start.linkTo( parent.start)
                end.linkTo(parent.end)
            })

        Text(text = if (viewModel.isLastIndex(selectedIndex ))stringResource(R.string.next) else stringResource(R.string.start),
            modifier = Modifier
                .constrainAs(next) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .clickable(enabled = true) { if(viewModel.isLastIndex(selectedIndex) )viewModel.setSelectedIndex(value=selectedIndex + 1)

                else                     navController.navigate(AuthNavScreen.LoginScreen.route)
                }
                .padding(10.dp)
        )

        Text(text = stringResource(R.string.skip),
            modifier = Modifier
                .constrainAs(skip) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .clickable(enabled = true) { navController.navigate(AuthNavScreen.LoginScreen.route) }
                .padding(10.dp)

        )
    }

}

@Composable fun PageItem(item: OnBoardingItem) {
    Column(  modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(item.icon), contentDescription = item.title)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text =item.title, color = Blue, fontSize = 17.sp,  textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text =item.content, color = Gray, fontSize = 13.sp,  textAlign = TextAlign.Center,
        )
    }
}
