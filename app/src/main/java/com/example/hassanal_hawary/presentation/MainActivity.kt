package com.example.hassanal_hawary.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hassanal_hawary.presentation.favorites.FavoriteScreen
import com.example.hassanal_hawary.presentation.main_screen.BottomMenu
import com.example.hassanal_hawary.presentation.main_screen.MainScreen
import com.example.hassanal_hawary.presentation.main_screen.menusItems
import com.example.hassanal_hawary.presentation.main_screen.programs
import com.example.hassanal_hawary.presentation.profile.ProfileScreen
import com.example.hassanal_hawary.presentation.register.RegisterScreen
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
                    val viewModel: MainViewModel by viewModels()

                    Column {
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            val navHost =
                                NavHost(navController, startDestination = "splash_screen") {

                                    composable("sign_in") {
                                        val signInViewModel = viewModel<SignInViewModel>()
                                        val state by signInViewModel.state.collectAsState()

                                        val launcher = rememberLauncherForActivityResult(
                                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                                            onResult = { activityResult ->
                                                if (activityResult.resultCode == RESULT_OK) {
                                                    lifecycleScope.launch {
                                                        val signInResult =
                                                            googleAuthUiClient.signInWithIntent(
                                                                intent = activityResult.data
                                                                    ?: return@launch
                                                            )
                                                        signInViewModel.onSignInResult(signInResult)
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
                                                signInViewModel.hideProgressBar()
                                                navController.navigate(
                                                    "profile"
                                                )
                                                viewModel.newNavigation("profile")
                                                Log.d("MainActivity", "onCreate: going to reset ViewModel")
                                                signInViewModel.resetState()
                                            }
                                        }
                                        LaunchedEffect(key1 = state.enterValidEmailMsg) {
                                            if (state.enterValidEmailMsg.isBlank()) return@LaunchedEffect
                                            Toast.makeText(
                                                applicationContext,
                                                state.enterValidEmailMsg,
                                                Toast.LENGTH_LONG
                                            ).show()


                                        }
                                        LaunchedEffect(key1 = state.enterValidPassowrdMsg) {
                                            if (state.enterValidPassowrdMsg.isBlank()) return@LaunchedEffect
                                            Toast.makeText(
                                                applicationContext,
                                                state.enterValidPassowrdMsg,
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }

                                        SignInScreen(
                                            state = state,
                                            onRegisterClick = {
                                                Toast.makeText(
                                                    applicationContext,
                                                    "Navigate to register screen",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                                navController.navigate("register")
                                            },
                                            // here there should be a progress bar
                                            onSignInClick = {
                                                // show progress bar

                                                signInViewModel.userClickSignInBtn()
                                            },
                                            onLoginRegisterElementClick = {
                                                signInViewModel.showProgressBar()
                                                lifecycleScope.launch {
                                                    val signInIntentSender =
                                                        googleAuthUiClient.signIn()
                                                    launcher.launch(
                                                        IntentSenderRequest.Builder(
                                                            signInIntentSender ?: return@launch
                                                        ).build()
                                                    )
                                                }
                                            },
                                            onEmailChange = {
                                                signInViewModel.emailChanged(it)
                                            },
                                            onPasswordChange = {
                                                signInViewModel.passwordChanged(it)
                                            }
                                        )
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
                                        /* LaunchedEffect(key1 = Unit) {
                                             if (googleAuthUiClient.getSignedInUser() != null) {
                                                 navController.navigate("profile")
                                             }
                                         }*/


                                        SplashScreen {
                                            if (googleAuthUiClient.getSignedInUser() != null)
                                                navController.navigate("profile")
                                            else
                                                navController.navigate("sign_in")
                                            // should navigate to the main screen composeable
                                        }
                                    }

                                    /*
                                    Hey Muhammed this function will display the image
                                    of Dr. Hassan app
                                     */
                                    composable("register") {
                                        RegisterScreen(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(15.dp),
                                            onLoginClick = {
                                                navController.navigate("sign_in")
                                            },
                                            onLoginRegisterElementClick = {

                                            },
                                            onRegisterClick = {
                                                navController.navigate("main_screen")
                                                viewModel.newNavigation("main_screen")
                                            }
                                        )
                                    }

                                    composable("main_screen") {
                                        MainScreen(
                                            modifier = Modifier.fillMaxSize(),
                                            navController = navController,
                                            programs = programs,
                                            onItemClick = { itemIndex ->
                                                viewModel.userClickItem(itemIndex)
                                            }
                                        )
                                    }
                                    composable("favorites") {
                                        FavoriteScreen(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(
                                                    horizontal = 15.dp
                                                )
                                        )
                                    }


                                }
                        }
                        navController.currentDestination?.run {
                            Log.d("MainAct", "onCreate: destination id is ${this.id}")
                            Log.d("MainAct", "onCreate: destination rout is ${this.route}")
                        }

                        if (viewModel.bottomBarState.value) {
                            BottomMenu(
                                onItemClick = {
                                    when (it) {
                                        0 -> {
                                            navController.navigate("main_screen")
                                        }
                                        2 -> {
                                            navController.navigate("favorites")
                                        }

                                        3 -> {
                                            navController.navigate("profile")
                                        }
                                    }
                                },
                                menuItems = menusItems
                            )
                        }

                    }


                }
            }
        }
    }

}


