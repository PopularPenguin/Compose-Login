package com.popularpenguin.composelogin.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popularpenguin.composelogin.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    var authState: AuthState by mutableStateOf(AuthState.LoggedOut())
        private set

    private val testUsers = mutableSetOf(
        User(
            name = "Test User",
            email = "m",
            password = "m"
        ),
        User(
            name ="Brian",
            email = "brian@hotmail.com",
            password = "hunter2"
        ),
        User(
            name = "Abraham Lincoln",
            email = "abraham.lincoln@gmail.com",
            password = "guywithahat"
        ),
        User(
            name = "George Washington",
            email = "george.washington@microsoft.com",
            password = "OGpresident"
        )
    )

    fun authenticateUser(email: String, password: String) {
        if (email.isBlank() or password.isBlank()) return

        viewModelScope.launch {
            authState = AuthState.LoggingIn()
            delay(500L) // simulate network delay

            testUsers.firstOrNull { it.email == email }?.let { user ->
                if (user.password == password) {
                    authState = AuthState.Success(user, "ACCESS_TOKEN")

                    return@launch
                }
            }

            authState = AuthState.Failure()
        }
    }

    fun logout() {
        authState = AuthState.LoggedOut()
    }

    sealed class AuthState {
        data class LoggedOut(val message: String = "") : AuthState()
        data class LoggingIn(val message: String = "") : AuthState()
        data class Success(val user: User, val authToken: String) : AuthState()
        data class Failure(val error: String = "") : AuthState()
    }
}