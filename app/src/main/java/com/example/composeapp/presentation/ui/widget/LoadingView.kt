package com.example.composeapp.presentation.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeapp.presentation.ui.theme.ComposeAppTheme
import com.example.composeapp.presentation.ui.theme.PurpleGrey80

@Preview(showBackground = true)
@Composable
fun LoadingView(){
    ComposeAppTheme {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().background(PurpleGrey80)
    ) {
        CircularProgressIndicator()
    }
    }
}
