package com.example.hassanal_hawary.presentation.sign_in

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hassanal_hawary.R
import com.example.hassanal_hawary.common.LoginRegisterProviderElement

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.errorMessage) {
        state.errorMessage?.let { error ->
            Toast.makeText(
                context, error, Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(0.5f))

        EmailPasswordSection()

        Spacer(modifier = Modifier.height(32.dp))

        loginRegisterSection(
            context = context,
            modifier = Modifier.fillMaxWidth(),
            isLogin = true
        ) {
            onRegisterClick()
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            onSignInClick()
        }) {
            Text(text = "Sign In")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailPasswordSection() {
    val emailState = rememberSaveable {
        mutableStateOf("")
    }
    val passwordState = rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        TextField(value = emailState.value, onValueChange = {
            emailState.value = it
        }, modifier = Modifier.fillMaxWidth(), singleLine = true, leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email, contentDescription = "Email Section"
            )
        }, label = {
            Text(
                text = "البريد الإلكتروني"
            )
        }

        )
        Spacer(modifier = Modifier.height(32.dp))

        TextField(value = passwordState.value, onValueChange = {
            passwordState.value = it
        }, modifier = Modifier.fillMaxWidth(), singleLine = true, leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock, contentDescription = "Password"
            )
        }, label = {
            Text(
                text = "كلمة السر"
            )
        })
    }
}

@Preview(name = "Sign In Screen", widthDp = 320)
@Composable
fun SignInScreenPreview() {
    SignInScreen(state = SignInState(),
        onRegisterClick = {

        },
        onSignInClick = {

        })
}

@Composable
fun loginRegisterSection(
    context: Context,
    modifier: Modifier,
    isLogin: Boolean,
    onLoginRegisterClick: () -> Unit

) {
    Row(
        modifier = Modifier, horizontalArrangement = Arrangement.SpaceAround
    ) {

        Text(
            text = if (isLogin) context.getString(R.string.login_question)
            else context.getString(R.string.register_question)
        )


        Text(
            text = if (isLogin) context.getString(R.string.login)
            else context.getString(R.string.register),
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.clickable {
                onLoginRegisterClick()
            }
        )
    }
}

@Composable
fun LoginRegisterProviders(
    modifier: Modifier,
    isLogin: Boolean,
    context: Context
) {

    Column(
        modifier = modifier
    ) {
        Text(
            text = if (isLogin) context.getString(R.string.register_using)
            else context.getString(R.string.login_using)
        )

        Spacer(modifier = Modifier.height(32.dp))

        


    }

}

@Composable
fun LoginRegisterProviderElements(
    modifier: Modifier,
    onProviderElementClick: (LoginRegisterProviderElement) -> Unit
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,


    ) {

    }
}