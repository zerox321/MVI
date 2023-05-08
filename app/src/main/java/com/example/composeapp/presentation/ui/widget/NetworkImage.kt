package com.example.composeapp.presentation.ui.widget

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
fun NetworkImage(url: String?="",contentDescription:String?=null, modifier: Modifier ) {
    SubcomposeAsyncImage(
        model = url,
        modifier=modifier,
        contentScale = ContentScale.Crop,
        contentDescription = contentDescription
    ){
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error)
            CircularProgressIndicator()
         else
            SubcomposeAsyncImageContent()

    }
}