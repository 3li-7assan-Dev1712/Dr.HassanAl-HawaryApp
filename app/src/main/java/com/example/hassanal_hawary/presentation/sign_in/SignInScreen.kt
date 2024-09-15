package com.example.hassanal_hawary.presentation.sign_in

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hassanal_hawary.R
import com.example.hassanal_hawary.common.LoginRegisterProviderElement


@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onLoginRegisterElementClick: (LoginRegisterProviderElement) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginBtnClick: () -> Unit
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
            .padding(horizontal = 15.dp, vertical = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /*LoginScreenGraphic(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.3f)
        )*/

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        Text(
            text = "Welcome you",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "In Dr Hassan App!",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        EmailPasswordSection(
            email = "",
            password = "",
            onEmailChange = { email ->
                onEmailChange(email)
            },
            onPasswordChange = { password ->
                onPasswordChange(password)
            }
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        LoginRegisterSection(
            LocalContext.current,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 15.dp,
                ),
            isLogin = true,
        ) {

        }

        Spacer(Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth(),

            onClick = {
                onLoginBtnClick()
            }
        ) {
            Text(
                text = "Login"
            )
        }

        if (state.showSignInProgressBar) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
            )

            // surfaceVariant
        }

//        Spacer(modifier = Modifier.height(20.dp))

        LoginRegisterProvidersSection(
            modifier = Modifier,
            context = context,
            isLogin = true,
            onElementClick = {
                onLoginRegisterElementClick(it)
            }
        )

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
                text = LocalContext.current.getString(R.string.enter_email)
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
                text = LocalContext.current.getString(R.string.enter_password)
            )
        })
    }
}

@Preview(name = "Sign In Screen", widthDp = 320, heightDp = 640)
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

        },
        onLoginBtnClick = {

        })
}

@Composable
fun LoginRegisterSection(
    context: Context,
    modifier: Modifier,
    isLogin: Boolean,
    onLoginRegisterClick: () -> Unit

) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = if (!isLogin) context.getString(R.string.login_question)
            else context.getString(R.string.register_question)
        )



        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = if (!isLogin) context.getString(R.string.login)
            else context.getString(R.string.register),
            modifier = Modifier.clickable {
                onLoginRegisterClick()
            },
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 18.sp
            )

        )
    }
}


@Composable
fun LoginScreenGraphic(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.primary
            )
            .padding(4.dp)
    )
}

@Preview(name = "graphic prev", heightDp = 400)
@Composable
private fun LoginScreenGraphicComp() {
    LoginScreenGraphic(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.25f)
    )
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
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(
                start = 16.dp
            )

        )

        Spacer(modifier = Modifier.height(16.dp))

        LoginRegisterProviderElements(
            modifier = Modifier.size(56.dp),
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

    Row {
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

        Spacer(modifier = Modifier.width(16.dp))

        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            ProviderElement(
                loginRegisterProviderElement = LoginRegisterProviderElement.GoogleElement,
                imageRes = R.drawable.facebook_icon,
                onElementClick = {
                    onElementClick(it)
                }
            )
        }
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
            .height(56.dp)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(size = 16.dp)
            )
            .background(color = Color.Black)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)

        )
    }


}

@Preview
@Composable
private fun EmailPasswordPrev() {

    EmailPasswordSection(
        email = "alihassan@gmail.com",
        password = "Dkfjdkf",
        {

        }
    ) { }
}