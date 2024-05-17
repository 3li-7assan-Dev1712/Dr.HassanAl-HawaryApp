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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.hassanal_hawary.presentation.all_articles.AllArticlesScreen
import com.example.hassanal_hawary.presentation.all_articles.AllArticlesViewModel
import com.example.hassanal_hawary.presentation.all_lectures.AllLecturesScreen
import com.example.hassanal_hawary.presentation.article_screen.ArticleScreen
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

class MainActivity : ComponentActivity(), NavController.OnDestinationChangedListener {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            onTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    private val viewModel: MainViewModel by viewModels()

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
                    navController.addOnDestinationChangedListener(this)

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
                                                    "lectures"
                                                )
                                                viewModel.newNavigation("lectures")
                                                Log.d(
                                                    "MainActivity",
                                                    "onCreate: going to reset ViewModel"
                                                )
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
                                                navController.navigate(
                                                    route = "register",
                                                    navOptions = NavOptions.Builder(

                                                    ).setLaunchSingleTop(true)
                                                        .build()

                                                )
                                                navController.clearBackStack("sign_in")
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
                                                navController.navigate("lectures")
                                            else
                                                navController.navigate("sign_in")
                                            // should navigate to the main screen composeable
                                        }
                                    }

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
                                                navController.navigate(
                                                    route = "main_screen",
                                                    navOptions = NavOptions.Builder(

                                                    ).setLaunchSingleTop(true)
                                                        .build()
                                                )
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
                                                navController.navigate("all_articles_screen")
                                                viewModel.newNavigation("all_articles_screen")
                                            }
                                        )
                                    }

                                    composable("lectures") {
                                        AllLecturesScreen()
                                    }


                                    navigation(
                                        startDestination = "all_articles_screen",
                                        route = "articles"
                                    ) {
                                        composable("all_articles_screen") {
                                            val viewModel =
                                                it.sharedViewModel<AllArticlesViewModel>(
                                                    navController
                                                )

                                            val allArtsState = viewModel.articlesState.collectAsState()

                                            if (allArtsState.value.articles.isEmpty()) {
                                                viewModel.fetchAllArticles()
                                            }

                                            AllArticlesScreen(
                                                modifier = Modifier.fillMaxSize(),
                                                allArtsState = allArtsState.value
                                            ) { articleIndex ->
                                                Log.d("00000", "onCreate: index is $articleIndex")
                                                navController.navigate(
                                                    route = "detail_screen/${articleIndex}"
                                                )
                                            }
                                        }

                                        composable(

                                            route = "detail_screen/{article_index}",
                                            arguments = listOf(
                                                navArgument("article_index") {
                                                    type = NavType.IntType
                                                }
                                            )

                                        ) {
                                            val clickedArticleIndex = it.arguments?.getInt("article_index") ?: 0
                                            Log.d("0000", "onCreate: rece index is $clickedArticleIndex")
                                            val viewModel =
                                                it.sharedViewModel<AllArticlesViewModel>(
                                                    navController
                                                )
                                            val allArtsState = viewModel.articlesState.collectAsState()
                                            val article = allArtsState.value.articles[clickedArticleIndex]
                                            ArticleScreen(article)
                                        }
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

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        controller.currentDestination?.route?.let { viewModel.newNavigation(it) }
    }

}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController
): T {

    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}


