package com.example.hassanal_hawary.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hassanal_hawary.presentation.profile.ProfileScreen
import com.example.hassanal_hawary.presentation.sign_in.GoogleAuthUiClient
import com.example.hassanal_hawary.presentation.sign_in.SignInScreen
import com.example.hassanal_hawary.presentation.sign_in.SignInViewModel
import com.example.hassanal_hawary.presentation.splash_screen.SplashScreen
import com.example.hassanal_hawary.ui.theme.HassanAlHawaryTheme
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            onTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HassanAlHawaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navHost = NavHost(navController, startDestination = "splash_screen") {

                        composable("sign_in") {
                            val viewModel = viewModel<SignInViewModel>()
                            val state by viewModel.state.collectAsState()

                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { activityResult ->
                                    if (activityResult.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUiClient.signInWithIntent(
                                                intent = activityResult.data ?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(key1 = state.isSignInSuccessful) {
                                if (state.isSignInSuccessful) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Sign in successful",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    navController.navigate(
                                        "profile"
                                    )
                                    viewModel.resetState()
                                }
                            }

                            SignInScreen(
                                state = state,
                                onRegisterClick = {
                                    Toast.makeText(
                                        applicationContext,
                                        "Navigate to register screen",
                                        Toast.LENGTH_LONG
                                    ).show()
                                },
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }


                                },
                                onLoginRegisterElementClick = {
                                    Toast.makeText(
                                        applicationContext,
                                        "Login via $it account",
                                        Toast.LENGTH_LONG
                                    ).show()
                                })
                        }
                        composable("profile") {
                            ProfileScreen(
                                userData = googleAuthUiClient.getSignedInUser(),
                                onSignOutClick = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        Toast.makeText(
                                            applicationContext,
                                            "Sign out",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        navController.popBackStack()
                                    }

                                }
                            )
                        }
                        composable("splash_screen") {
                            SplashScreen {
                                /* LaunchedEffect(key1 = Unit) {
                                     if (googleAuthUiClient.getSignedInUser() != null) {
                                         navController.navigate("profile")
                                     }
                                 }*/

                                if (googleAuthUiClient.getSignedInUser() != null)
                                    navController.navigate("profile")
                                else
                                    navController.navigate("sign_in")
                                // should navigate to the main screen composeable
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HassanAlHawaryTheme {
        Greeting("Android")
    }
}