package com.popularpenguin.composelogin.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.popularpenguin.composelogin.viewmodel.AuthViewModel

@ExperimentalComposeUiApi
@Composable
fun LoginEmailTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
) {
    LoginTextField(
        modifier = modifier,
        text = text,
        onTextChange = onTextChange,
        label = "User Email",
        keyboardType = KeyboardType.Email
    )
}

@ExperimentalComposeUiApi
@Composable
fun LoginPasswordTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
) {
    LoginTextField(
        modifier = modifier,
        text = text,
        onTextChange = onTextChange,
        label = "Password",
        keyboardType = KeyboardType.Password,
        visualTransformation = PasswordVisualTransformation()
    )
}

@ExperimentalComposeUiApi
@Composable
private fun LoginTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = onTextChange,
        label = { Text(text = label) },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = keyboardType),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
        visualTransformation = visualTransformation
    )
}

@Composable
fun LoginMessageText(modifier: Modifier = Modifier, authState: AuthViewModel.AuthState) {
    val text: String
    val color: Color
    when (authState) {
        is AuthViewModel.AuthState.LoggedOut -> {
            text = "Logged out"
            color = Color.Black
        }
        is AuthViewModel.AuthState.Success -> {
            return
        }
        is AuthViewModel.AuthState.Failure -> {
            text = "Invalid user name or password"
            color = Color.Red
        }
        is AuthViewModel.AuthState.LoggingIn -> {
            text = "Logging in..."
            color = Color.Blue
        }
    }

    Text(
        modifier = modifier,
        text = text,
        color = color
    )
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun LoginTextFieldPreview() {
    LoginEmailTextField(text = "", onTextChange = {})
}