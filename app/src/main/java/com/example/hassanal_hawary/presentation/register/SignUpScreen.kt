package com.example.hassanal_hawary.presentation.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hassanal_hawary.R
import com.example.hassanal_hawary.common.LoginRegisterProviderElement
import com.example.hassanal_hawary.presentation.sign_in.EmailPasswordSection
import com.example.hassanal_hawary.presentation.sign_in.LoginRegisterProvidersSection
import com.example.hassanal_hawary.presentation.sign_in.LoginRegisterSection

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * This function will not be used there are going to be only one
 * for both login and register using Google play account for users
 */



@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onLoginRegisterElementClick: (LoginRegisterProviderElement) -> Unit,
    onRegisterClick: () -> Unit
) {
    val context = LocalContext.current
    val signUpViewModel: SignUpViewModel = viewModel()
    val signUpState = signUpViewModel.signupState.collectAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NameTextField()

        Spacer(modifier = Modifier.height(32.dp))

        EmailPasswordSection(
            email = signUpState.value.email,
            password = signUpState.value.password,
            onEmailChange = { enteredEmail ->
                signUpViewModel.emailChanged(enteredEmail)
            },
            onPasswordChange = { newPassword ->
                signUpViewModel.passwordChanged(newPassword)
            },
            emailError = signUpState.value.emailError,
            passwordError = signUpState.value.passwordError

        )

        Spacer(modifier = Modifier.height(32.dp))

        LoginRegisterSection(
            context = context,
            modifier = Modifier.fillMaxWidth(),
            isLogin = false,
        ) {
            onLoginClick()
        }

        LoginRegisterProvidersSection(
            modifier = Modifier.weight(1f),
            context = context,
            isLogin = false,
            onElementClick = {
                onLoginRegisterElementClick(it)
            }
        )

        Button(onClick = {
//            onRegisterClick()
            val email = signUpState.value.email
            val password = signUpState.value.password
            signUpViewModel.signUp(email, password)

        }) {
            Text(text = "Register")
        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameTextField() {
    val nameState = rememberSaveable {
        mutableStateOf("")
    }
    OutlinedTextField(value = nameState.value, onValueChange = {
        nameState.value = it
    }, modifier = Modifier.fillMaxWidth(), singleLine = true, leadingIcon = {
        Icon(
            imageVector = Icons.Default.Email, contentDescription = "Email Section"
        )
    }, label = {
        Text(
            text = LocalContext.current.getString(R.string.name)
        )
    }

    )
}

@Preview
@Composable
private fun RegisterScreenPrev() {
    SignUpScreen(onLoginClick = { }, onLoginRegisterElementClick = {}) {

    }
}