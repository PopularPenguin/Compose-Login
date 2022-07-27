package com.popularpenguin.composelogin.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.popularpenguin.composelogin.model.User
import com.popularpenguin.composelogin.ui.theme.ComposeLoginTheme
import com.popularpenguin.composelogin.viewmodel.AuthViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    authState: AuthViewModel.AuthState,
    onLogOut: () -> Unit
) {
    // Launch login screen if user isn't logged in
    if (authState !is AuthViewModel.AuthState.Success) {
        navController.navigateUp()
    } else {
        HomeContent(
            user = authState.user,
            onLogOut = onLogOut
        )
    }
}

@Composable
fun HomeContent(
    user: User,
    onLogOut: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Welcome ${user.name}",
            style = TextStyle(fontSize = 24.sp)
        )
        Button(onClick = onLogOut) {
            Text("Log out")
        }
    }
}

@Preview
@SuppressWarnings
@Composable
fun HomePreview() {
    ComposeLoginTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            HomeContent(user = User("NULL"), onLogOut = {})
        }
    }
}