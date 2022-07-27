package com.popularpenguin.composelogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.popularpenguin.composelogin.ui.HomeScreen
import com.popularpenguin.composelogin.ui.LoginScreen
import com.popularpenguin.composelogin.ui.theme.ComposeLoginTheme
import com.popularpenguin.composelogin.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainActivityScreen()
                }
            }
        }
    }
}

@Composable
private fun MainActivityScreen() {
    val navController = rememberNavController()
    val viewModel: AuthViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                navController = navController,
                authState = viewModel.authState,
                onLogin = viewModel::authenticateUser
            )
        }
        composable("home") {
            HomeScreen(
                navController = navController,
                authState = viewModel.authState,
                onLogOut = viewModel::logout
            )
        }
    }
}