package com.example.hassanal_hawary.presentation.all_articles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hassanal_hawary.domain.model.Article
import com.example.hassanal_hawary.presentation.favorites.Articles

@Composable
fun AllArticlesScreen(
    modifier: Modifier = Modifier,
    allArtsState: AllArticlesState,
    onArticleClick: (Int) -> Unit
) {




    if (allArtsState.showProgress) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp)

            )
        }
    }

    if (!allArtsState.errorMessage.isNullOrBlank()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = allArtsState.errorMessage ?: "Error",
                fontSize = 22.sp,
                color = Color.Red
            )
        }
    }

    Articles(
        modifier = modifier,
        articles = allArtsState.articles
    ) {
        onArticleClick(it)
    }

}

/*

@Preview(widthDp = 320, heightDp = 640)
@Composable
private fun AllArticlesPrev() {
    AllArticlesScreen(
        modifier = Modifier.fillMaxSize(),
        allArtsState = AllArticlesState(false, null, listOf()) {

        }
    )

}*/
