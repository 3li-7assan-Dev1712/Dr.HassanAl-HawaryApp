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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.hassanal_hawary.common.LoginRegisterProviderElement
import com.example.hassanal_hawary.presentation.sign_in.EmailPasswordSection
import com.example.hassanal_hawary.presentation.sign_in.LoginRegisterProvidersSection
import com.example.hassanal_hawary.presentation.sign_in.loginRegisterSection

/**
 * This function will display the registration screen for taking
 * some data from the user (name + email) and then navigate to the
 * main screen where they can see the videos and audios of the Dr. Hassan
 * Al-Hwary
 */
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onLoginRegisterElementClick: (LoginRegisterProviderElement) -> Unit,
    onRegisterClick: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NameTextField()

        Spacer(modifier = Modifier.height(32.dp))

        EmailPasswordSection(
            email = "",
            password = "",
            onEmailChange = {

        },
            onPasswordChange = {

            })

        Spacer(modifier = Modifier.height(32.dp))

        loginRegisterSection(
            context = context,
            modifier = Modifier.fillMaxWidth(),
            isLogin = false
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
            onRegisterClick()
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
    TextField(value = nameState.value, onValueChange = {
        nameState.value = it
    }, modifier = Modifier.fillMaxWidth(), singleLine = true, leadingIcon = {
        Icon(
            imageVector = Icons.Default.Email, contentDescription = "Email Section"
        )
    }, label = {
        Text(
            text = "الإســـم"
        )
    }

    )
}