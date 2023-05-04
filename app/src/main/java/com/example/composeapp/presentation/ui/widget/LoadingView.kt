package com.example.composeapp.presentation.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapp.presentation.ui.theme.ComposeAppTheme
import com.example.composeapp.presentation.ui.theme.INPUTBG
import com.example.composeapp.presentation.ui.theme.LoadingBackGround

@Preview(showBackground = true)
@Composable
fun LoadingView(){
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = LoadingBackGround
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }

    }
}
