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
import com.example.hassanal_hawary.articles_list.AllArticlesScreen
import com.example.hassanal_hawary.articles_list.AllArticlesViewModel
import com.example.hassanal_hawary.audios_list.AllLecturesScreen
import com.example.hassanal_hawary.article_detail.ArticleScreen
import com.example.hassanal_hawary.favorites.FavoriteScreen
import com.example.hassanal_hawary.audio_detail.LectureScreen
import com.example.hassanal_hawary.mainscreen.BottomMenu
import com.example.hassanal_hawary.mainscreen.MainScreen
import com.example.hassanal_hawary.mainscreen.menusItems
import com.example.hassanal_hawary.mainscreen.programs
import com.example.hassanal_hawary.profile.ProfileScreen
import com.example.hassanal_hawary.register.SignUpScreen
import com.example.hassanal_hawary.login.GoogleAuthUiClient
import com.example.hassanal_hawary.login.SignInScreen
import com.example.hassanal_hawary.login.SignInViewModel
import com.example.hassanal_hawary.splash_screen.SplashScreen
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
                                            onNavigateTo = { r ->
                                                navController.navigate(
                                                    route = r,
                                                    navOptions = NavOptions.Builder()
                                                        .setLaunchSingleTop(true)
                                                        .setPopUpTo(r, false)
                                                        .build()
                                                )
//                                                navController.clearBackStack(route = r)
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
           fl7nbbbbbbbbbbbbbbbjkjdkfjdkjflaj;aj;lkjasdfalifgffgg                              }*/


                                        /*
                                           this is a function to build a native app development
                                           in order to build such an app you need to learn a lot
                                           of new courses so what do your thick about
                                           doing that
                                           I am really happy about the new keyboard
                                           so in the upp coming days I will be building new
                                           apps this keyboard is so1        
                                         */
                                        SplashScreen {
                                            if (googleAuthUiClient.getSignedInUser() != null) {
                                                navController.navigate("main_screen")
                                                viewModel.newNavigation("main_screen")
                                            } else {
                                                navController.navigate("sign_in")
                                                viewModel.newNavigation("sign_in")
                                            }
                                            // should navigate to the main screen composeable
                                        }
                                    }

                                    composable("register") {
                                        SignUpScreen(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(15.dp),
                                            onLoginClick = {
                                                navController.navigate("sign_in")
                                            },
                                            onLoginRegisterElementClick = {

                                            },

                                            onNavigateToRoute = { route ->
                                                navController.navigate(
                                                    route = route,
                                                    navOptions = NavOptions.Builder(

                                                    ).setLaunchSingleTop(true)
                                                        .build()
                                                )
                                                viewModel.newNavigation(route)
                                            }
                                        )
                                    }

                                    composable("main_screen") {
                                        MainScreen(
                                            modifier = Modifier.fillMaxSize(),
                                            programs = programs,
                                            onItemClick = { itemIndex ->
                                                when (itemIndex) {
                                                    0 -> {
                                                        viewModel.userClickItem(itemIndex)
                                                        navController.navigate("all_articles_screen")
                                                        viewModel.newNavigation("all_articles_screen")
                                                    }

                                                    1 -> {
                                                        viewModel.userClickItem(itemIndex)
                                                        navController.navigate("lectures")
                                                        viewModel.newNavigation("lectures")
                                                    }
                                                }

                                            }
                                        )
                                    }

                                    /*navigation(
                                        startDestination = "lectures",
                                        navController = navController,
                                        navArgument()
                                    ) {

                                    }*/

                                    composable("lectures") {
                                        AllLecturesScreen(
                                            modifier = Modifier.fillMaxSize()
                                        ) { lecName ->
                                            navController.navigate("lecture_screen/$lecName")
                                        }
                                    }

                                    composable(
                                        route = "lecture_screen/{lecture_name}",
                                        arguments = listOf(
                                            navArgument("lecture_name") {
                                                type = NavType.StringType
                                            }
                                        )
                                    ) {
                                        val lectureName =
                                            it.arguments?.getString("lecture_name") ?: "97.mp3"
                                        LectureScreen(
                                            modifier = Modifier.fillMaxSize(),
                                            lectureName = lectureName
                                        )
                                    }

                                    navigation(
                                        startDestination = "splash_screen",
                                        route = "articles"
                                    ) {
                                        composable("all_articles_screen") {
                                            val viewModel =
                                                it.sharedViewModel<AllArticlesViewModel>(
                                                    navController
                                                )

                                            val allArtsState =
                                                viewModel.articlesState.collectAsState()

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
                                            val clickedArticleIndex =
                                                it.arguments?.getInt("article_index") ?: 0
                                            Log.d(
                                                "0000",
                                                "onCreate: rece index is $clickedArticleIndex"
                                            )
                                            val viewModel =
                                                it.sharedViewModel<AllArticlesViewModel>(
                                                    navController
                                                )
                                            val allArtsState =
                                                viewModel.articlesState.collectAsState()
                                            val article =
                                                allArtsState.value.articles[clickedArticleIndex]
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


