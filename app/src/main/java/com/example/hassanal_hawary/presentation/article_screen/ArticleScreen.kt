package com.example.hassanal_hawary.presentation.article_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hassanal_hawary.R
import com.example.hassanal_hawary.domain.model.Article


/*
In this screen the articles will be  visible so that the user can easily
navigate from one article to another to read and share them
 */
@Composable
fun ArticleScreen(
    article: Article
) {

    val articleScreenViewModel = viewModel<ArticleScreenViewModel>()

    Column (
        modifier = Modifier.fillMaxSize()
    ){

        // title text
        Text(
            text = article.title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.clickable {
                articleScreenViewModel.uploadFakeArticle()
                val art = articleScreenViewModel.getArticle()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ImageContainer(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.2f)
                .border(
                    width = 4.dp, shape = RoundedCornerShape(30.dp),
                    color = MaterialTheme.colorScheme.tertiary
                ),
            imageRes = R.drawable.ic_launcher_background
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Box(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = article.content,
                style = MaterialTheme.typography.displayMedium
            )
        }


    }
}

@Preview(widthDp = 320, heightDp = 720)
@Composable
fun ArticleScreenPreview() {

    ArticleScreen(Article("Title", "Content"))
}

@Composable
fun ImageContainer(
    modifier: Modifier,
    imageRes: kotlin.Int
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = "Article Image"
        )
    }

}

