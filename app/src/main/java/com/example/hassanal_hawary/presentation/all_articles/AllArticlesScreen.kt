package com.example.hassanal_hawary.presentation.all_articles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.example.hassanal_hawary.presentation.favorites.Articles

@Composable
fun AllArticlesScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null
) {

    val viewModel = viewModel<AllArticlesViewModel>()
    val allArtsState = viewModel.articlesState.collectAsState()
    viewModel.fetchAllArticles()

    if (allArtsState.value.showProgress) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp)

            )
        }
    }

    if (!allArtsState.value.errorMessage.isNullOrBlank()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = allArtsState.value.errorMessage ?: "Error",
                fontSize = 22.sp,
                color = Color.Red
            )
        }
    }

    Articles(
        modifier = modifier,
        articles = allArtsState.value.articles
    ) { clickedArticle ->
        // navigate to detail article
        navController?.navigate(
            route = "article_screen",

        )
    }

}


@Preview(widthDp = 320, heightDp = 640)
@Composable
private fun AllArticlesPrev() {
    AllArticlesScreen(
        modifier = Modifier.fillMaxSize()
    )

}