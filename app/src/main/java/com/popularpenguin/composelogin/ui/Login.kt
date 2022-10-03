package com.popularpenguin.composelogin.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.popularpenguin.composelogin.R
import com.popularpenguin.composelogin.ui.theme.*
import com.popularpenguin.composelogin.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    authState: AuthViewModel.AuthState,
    onLogin: (String, String) -> Unit
) {
    // On successful login, switch to 'Home' page
    if (authState is AuthViewModel.AuthState.Success) {
        navController.navigate(route = "home") {
            launchSingleTop = true
        }
    } else {
        LoginContent(authState, onLogin)
    }
}

@Composable
fun LoginContent(
    authState: AuthViewModel.AuthState,
    onLogin: (String, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    0.0f to ThemeGradientStart,
                    0.5f to ThemeGradientMid,
                    0.7f to ThemeGradientEnd
                )
            )
            .verticalScroll(state = rememberScrollState(), enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header image
        Image(
            modifier = Modifier.padding(all = 32.dp),
            painter = painterResource(id = R.drawable.img),
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = "Login",
            color = Color.Black,
            fontSize = 48.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .clip(RoundedCornerShape(25))
                .border(3.dp, Color.Black, RoundedCornerShape(25)),
            elevation = 10.dp
        ) {
            CardContent(authState, onLogin)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CardContent(authState: AuthViewModel.AuthState, onLogin: (String, String) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(brush = Brush.verticalGradient(listOf(Color.White, Color.LightGray)))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val (email, setEmail) = remember { mutableStateOf("") }
        val (password, setPassword) = remember { mutableStateOf("") }

        // Email
        LoginEmailTextField(
            modifier = Modifier.padding(top = 16.dp),
            text = email,
            onTextChange = setEmail
        )

        // Password
        LoginPasswordTextField(
            modifier = Modifier.padding(top = 32.dp),
            text = password,
            onTextChange = setPassword
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Button - Log in
        Button(
            modifier = Modifier.width(256.dp),
            colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Black),
            shape = RoundedCornerShape(35),
            onClick = { onLogin(email, password) }
        ) {
            Text(text = "Log in", color = Color.White)
        }

        LoginMessageText(
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
            authState = authState
        )
    }
}

@Preview
@Composable
fun LoginPreview() {
    LoginContent(AuthViewModel.AuthState.LoggedOut()) { _, _ -> }
}