package com.example.hassanal_hawary.presentation.sign_in

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hassanal_hawary.R
import com.example.hassanal_hawary.common.LoginRegisterProviderElement


@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onLoginRegisterElementClick: (LoginRegisterProviderElement) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {

    /*val emailState = rememberSaveable {
        mutableStateOf("")
    }
    val passwordState = rememberSaveable {
        mutableStateOf("")
    }
    */
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

        EmailPasswordSection(
            email = state.enteredEmail,
            password = state.enteredPassword,
            onEmailChange = { enteredEmail ->
                onEmailChange(enteredEmail)
            },
            onPasswordChange = { enteredPassword ->
                onPasswordChange(enteredPassword)
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        loginRegisterSection(
            context = context,
            modifier = Modifier.fillMaxWidth(),
            isLogin = true
        ) {
            onRegisterClick()
        }

        LoginRegisterProvidersSection(
            modifier = Modifier.weight(1f),
            context = context,
            isLogin = true,
            onElementClick = {
                onLoginRegisterElementClick(it)
            }
        )

        Button(onClick = {
            onSignInClick()
        }) {
            Text(text = "Sign In")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailPasswordSection(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {


    Column {
        OutlinedTextField(value = email, onValueChange = {
            onEmailChange(it)
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

        OutlinedTextField(value = password, onValueChange = {
            onPasswordChange(it)
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

        },
        onLoginRegisterElementClick = {

        },
        onEmailChange = {

        },
        onPasswordChange = {

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
        modifier = modifier, horizontalArrangement = Arrangement.SpaceAround
    ) {

        Text(
            text = if (!isLogin) context.getString(R.string.login_question)
            else context.getString(R.string.register_question)
        )


        Text(
            text = if (!isLogin) context.getString(R.string.login)
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
fun LoginRegisterProvidersSection(
    modifier: Modifier,
    context: Context,
    isLogin: Boolean,
    onElementClick: (LoginRegisterProviderElement) -> Unit
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (isLogin) context.getString(R.string.register_using)
            else context.getString(R.string.login_using),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                start = 16.dp
            )

        )

        LoginRegisterProviderElements(
            modifier = Modifier.fillMaxWidth(),
            onElementClick = {
                onElementClick(it)
            })

    }

}

@Composable
fun LoginRegisterProviderElements(
    modifier: Modifier = Modifier,
    onElementClick: (LoginRegisterProviderElement) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        ProviderElement(
            loginRegisterProviderElement = LoginRegisterProviderElement.GoogleElement,
            imageRes = com.google.android.gms.base.R.drawable.googleg_standard_color_18,
            onElementClick = {
                onElementClick(it)
            }
        )
    }

}

@Composable
fun ProviderElement(
    loginRegisterProviderElement: LoginRegisterProviderElement,
    imageRes: Int,
    onElementClick: (LoginRegisterProviderElement) -> Unit
) {


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable {
                onElementClick(loginRegisterProviderElement)
            }
            .size(120.dp)
            .clip(
                CircleShape
            )
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier

        )
    }


}