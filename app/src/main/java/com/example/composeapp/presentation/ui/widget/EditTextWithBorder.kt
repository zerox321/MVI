package com.example.composeapp.presentation.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composeapp.presentation.ui.theme.Green
import com.example.composeapp.presentation.ui.theme.INPUTBG

@Composable
fun EditTextWithBorder(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier : Modifier
) {
    var isFocused by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,

        modifier = modifier
            .fillMaxWidth(0.9f)
            .background(
                color = INPUTBG,
                shape = RoundedCornerShape(8.dp)
            )
            .onFocusChanged { focusState -> isFocused = focusState.isFocused }
            .border(
                width =1.dp ,
                color =if(isFocused) Green else INPUTBG,
                shape = RoundedCornerShape(8.dp)
            ),

        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor  = Color.Transparent
            ),
        placeholder = {
            Text(
                textAlign = TextAlign.Start,
                        text = hint,
                style = TextStyle(color = Color.Black)
            )
        }
    )


}