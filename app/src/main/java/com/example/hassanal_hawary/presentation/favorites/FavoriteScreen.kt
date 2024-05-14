package com.example.hassanal_hawary.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hassanal_hawary.domain.model.Article
import com.example.hassanal_hawary.presentation.main_screen.MainScreenGridItem
import com.example.hassanal_hawary.ui.theme.Blue40

@Composable
fun FavoriteScreen(
    modifier: Modifier
) {

    Articles(
        modifier = Modifier.padding(horizontal = 15.dp),
        articles = provideFakeArticles()
    ) {

    }

}

@Composable
fun Articles(
    modifier: Modifier,
    articles: List<Article>,
    onArticleClick: (Int) -> Unit
) {

    LazyColumn(
        modifier = modifier
    ) {
        /*item {
            Text(
                text = context.getString(R.string.favorite_articles),
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(8.dp))
        }*/
        items(
            count = articles.size
        ) {
            MainScreenGridItem(
                programType = articles[it].title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
                    .background(
                        color = Blue40,
                        shape = RoundedCornerShape(size = 16.dp)
                    ),
                gridItemIndex = it,
                contentAlignment = Alignment.Center
            ) { articleIndex ->
                onArticleClick(articleIndex)
            }
        }

    }

}


fun provideFakeArticles(): List<Article> {
    return listOf(
        Article("أحكام المرأة في الاسلام", "kfjdkfj "),
        Article("أحكام المرأة في الاسلام", "kfjdkfj "),
        Article("أحكام المرأة في الاسلام", "kfjdkfj "),
        Article("أحكام المرأة في الاسلام", "kfjdkfj "),
        Article("أحكام المرأة في الاسلام", "kfjdkfj "),
        Article("أحكام المرأة في الاسلام", "kfjdkfj "),
        Article("أحكام المرأة في الاسلام", "kfjdkfj "),
        Article("أحكام المرأة في الاسلام", "kfjdkfj "),
        Article("أحكام المرأة في الاسلام", "kfjdkfj "),
        Article("أحكام المرأة في الاسلام", "kfjdkfj "),
        Article("أحكام المرأة في الاسلام", "kfjdkfj "),
    )
}