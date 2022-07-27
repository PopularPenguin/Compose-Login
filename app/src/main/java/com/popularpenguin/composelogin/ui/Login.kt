package com.popularpenguin.composelogin.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.popularpenguin.composelogin.R
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginContent(
    authState: AuthViewModel.AuthState,
    onLogin: (String, String) -> Unit
) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

    Column(
        Modifier
            .verticalScroll(state = rememberScrollState(), enabled = true)
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header image
        Image(
            modifier = Modifier.padding(top = 64.dp, bottom = 64.dp),
            painter = painterResource(id = R.drawable.login),
            contentDescription = null
        )

        // Email
        LoginEmailTextField(
            text = email,
            onTextChange = setEmail
        )

        // Password
        LoginPasswordTextField(
            modifier = Modifier.padding(top = 32.dp),
            text = password,
            onTextChange = setPassword
        )

        // Button - Log in
        Button(
            modifier = Modifier.padding(32.dp),
            onClick = { onLogin(email, password) }
        ) {
            Text("Log in")
        }

        LoginMessageText(
            modifier = Modifier.padding(32.dp),
            authState = authState
        )
    }
}