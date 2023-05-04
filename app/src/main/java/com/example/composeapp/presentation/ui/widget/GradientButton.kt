package com.example.composeapp.presentation.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.presentation.ui.theme.Blue
import com.example.composeapp.presentation.ui.theme.Green

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    gradient: Brush = Brush.horizontalGradient(
        colors = listOf(
            Blue,
            Green
        )
    )
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier

            .fillMaxWidth(0.9f)
            .background(
                brush = gradient,
                shape = RoundedCornerShape(16)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 32.dp, vertical = 12.dp)
    ) {
        Text(text =text, color = Color.White, fontSize = 17.sp,  textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold)


    }
}