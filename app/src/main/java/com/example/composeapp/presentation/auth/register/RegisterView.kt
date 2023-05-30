package com.example.composeapp.presentation.auth.register

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.composeapp.presentation.MenuActivity
import com.example.composeapp.presentation.auth.login.LoginViewModel
import com.example.composeapp.presentation.auth.navigation.AuthNavScreen
import com.example.composeapp.presentation.ui.theme.Blue
import com.example.composeapp.presentation.ui.widget.EditTextWithBorder
import com.example.composeapp.presentation.ui.widget.GradientButton
import com.example.composeapp.presentation.ui.widget.LoadingView
import com.example.composeapp.utility.ToastUtil.showError
import com.example.core.state.LoginViewEvents
import com.example.core.state.LoginViewStates
import kotlinx.coroutines.channels.Channel


@Composable
    fun RegisterView (navController: NavController){

    val viewModel: LoginViewModel = hiltViewModel()


    val state  by viewModel.states.collectAsState(initial = LoginViewStates.Idle)

    val phoneNumber  by viewModel.phoneNumber.collectAsState()
    val password  by viewModel.password.collectAsState()
    val context = LocalContext.current


    ConstraintLayout(modifier  = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {

        val (header,headerText,phoneHeader ,phoneInput ,passwordHeader,passwordInput,loginButton,createAccount,partnerAccount) = createRefs()

        Image(painter = painterResource(R.drawable.login_vector), contentDescription = stringResource(R.string.phone_number),modifier = Modifier.constrainAs(header) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }.fillMaxWidth()
        )

        Text(text = stringResource(R.string.register),
            color = Color.White, fontSize = 24.sp,
            modifier = Modifier
                .constrainAs(headerText) {
                    top.linkTo(header.top)
                    bottom.linkTo(header.bottom)
                    end.linkTo(header.end, 24.dp)
                }

        )
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


        Text(text =stringResource(R.string.password), color = Blue, fontSize = 17.sp,modifier = Modifier.constrainAs(passwordHeader) {
            top.linkTo(phoneInput.bottom,16.dp)
            start.linkTo(parent.start,16.dp)
        }, fontWeight = FontWeight.Bold)

        EditTextWithBorder(
            hint = stringResource(R.string.enterPassword),
            value = password,
            onValueChange = { password -> viewModel.setPassword(password=password) },
            modifier= Modifier.constrainAs(passwordInput) {
                top.linkTo(passwordHeader.bottom,16.dp)
                start.linkTo(parent.start,16.dp)
                end.linkTo(parent.end,16.dp)
            }
        )


        GradientButton(
            text = stringResource(id = R.string.register),
            onClick = { viewModel.onEvent(event = LoginViewEvents.Login(phoneNumber=phoneNumber,password=password)) },
            modifier= Modifier.constrainAs(loginButton) {
                top.linkTo(passwordInput.bottom,16.dp)
                start.linkTo(parent.start,16.dp)
                end.linkTo(parent.end,16.dp)
            }
        )
       



    }
    when (state){
        LoginViewStates.EmptyPassword -> context.showError(message = "Enter Password")
        LoginViewStates.EmptyPhoneNumber -> context.showError(message = "Enter Phone Number")
        LoginViewStates.Idle -> Unit
        LoginViewStates.Loading -> LoadingView()
        LoginViewStates.LoginSuccess -> context.startActivity(Intent(context, MenuActivity::class.java))
    }



    }

