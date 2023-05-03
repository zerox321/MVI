package com.example.composeapp.presentation.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composeapp.R
import com.example.composeapp.presentation.ui.theme.Blue
import com.example.composeapp.presentation.ui.widget.EditTextWithBorder


@Composable
    fun LoginView (navController: NavController){

    val viewModel: LoginViewModel = hiltViewModel()
    val phoneNumber  by viewModel.phoneNumber.collectAsState()

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (header,phoneHeader ,phoneInput ,next,skip) = createRefs()

        Image(painter = painterResource(R.drawable.login_vector), contentDescription = stringResource(R.string.phone_number),modifier = Modifier.constrainAs(header) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        Text(text =stringResource(R.string.phone_number), color = Blue, fontSize = 17.sp,modifier = Modifier.constrainAs(phoneHeader) {
            top.linkTo(header.bottom,16.dp)
            start.linkTo(parent.start,16.dp)
        }, fontWeight = FontWeight.Bold)

        EditTextWithBorder(
            hint = stringResource(R.string.enterPhoneNumber),
            value = phoneNumber,
            onValueChange = { phone -> viewModel.setPhoneNumber(phone=phone) },
            modifier= Modifier.constrainAs(phoneInput) {
                top.linkTo(phoneHeader.bottom,16.dp)
                start.linkTo(parent.start,16.dp)
                end.linkTo(parent.end,16.dp)
            }
        )

    }
    }

