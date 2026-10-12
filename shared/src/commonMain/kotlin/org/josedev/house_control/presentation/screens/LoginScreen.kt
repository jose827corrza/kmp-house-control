package org.josedev.house_control.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.josedev.house_control.navigation.routes.AppRoute
import org.josedev.house_control.presentation.repository.LoginEvent
import org.josedev.house_control.presentation.viewmodels.LoginViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    nav: NavController,
    loginViewModel: LoginViewModel = koinInject()
) {

    val loginState by loginViewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isPasswordVisible by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(IntrinsicSize.Max),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        OutlinedTextField(
            singleLine = true,
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(autoCorrect = false, keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            singleLine = true,
            value = password,
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
//                    Icon(
//                        imageVector = if (isPasswordVisible) , TODO
//                        contentDescription = "Check"
//                    )
                }

            },
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
            visualTransformation = if (isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None
        )
        Spacer(modifier = Modifier.height(40.dp))
        FilledTonalButton(
            onClick = {
                scope.launch {
                    loginViewModel.onEvent(LoginEvent.Login(email, password))
                    delay(1200L)
                    if (loginViewModel.uiState.value.authInfo != null) {
                        nav.navigate(AppRoute.HomeScreen().route)
                    } else if (loginState.errorMessage != null) {
//                    Toast.makeText(context, "Check your credentials", Toast.LENGTH_LONG).show()
                    }
                }

            },
            modifier = Modifier.width(200.dp)
        )
        {
            Text(text = "Log in")
        }
//        ElevatedButton(onClick = {
//            scope.launch {
//                loginViewModel.onEvent(LoginEvent.SignUp(email, password))
//                delay(1200L)
//                if(loginState.user != null){
//                    nav.navigate(AppNavigation.TasksScreen().route)
//                }else {
////                    Toast.makeText(context, "Error signing up", Toast.LENGTH_LONG).show()
//                }
//
//            }
//        },
//            modifier = Modifier.width(200.dp))
//        {
//            Text(text = "Sign up")
//        }
    }
    if (loginState.errorMessage != null) {
//        Toast.makeText(context, "Check your credentials.", Toast.LENGTH_LONG).show()
    }
}