package com.example.composeapp.presentation.auth.onBoarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen (navController: NavController){

    val items by lazy {  listOf(OnBoardingItem.FirstPage, OnBoardingItem.SecondPage, OnBoardingItem.ThirdPage) }
    val itemsCount by lazy { items.size }
    fun isLastIndex (index:Int) = index < itemsCount-1
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (pager,indicator ,next,skip) = createRefs()

        HorizontalPager(pageCount = itemsCount, state = pagerState,  modifier = Modifier.constrainAs(pager) {
            top.linkTo(parent.top)
            bottom.linkTo(indicator.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) { page -> PageItem(item= items[page]) }

        PageIndicator(selectedPage = pagerState.currentPage,numberOfPages=itemsCount ,
            modifier = Modifier.constrainAs(indicator) {
                top.linkTo(next.top)
                bottom.linkTo(next.bottom)
                start.linkTo( parent.start)
                end.linkTo(parent.end)
            })

        Text(text = if (isLastIndex(pagerState.currentPage ))stringResource(R.string.next) else stringResource(R.string.start),
            modifier = Modifier
                .constrainAs(next) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .clickable(enabled = true) {

                    coroutineScope.launch {
                        if(isLastIndex(pagerState.currentPage) )pagerState.animateScrollToPage(pagerState.currentPage+1)
                        else                     navController.navigate(AuthNavScreen.LoginScreen.route)
                    }

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
