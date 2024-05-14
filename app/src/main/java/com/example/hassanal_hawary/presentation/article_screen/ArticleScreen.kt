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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
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




        LazyColumn (
            modifier = Modifier.padding(horizontal = 15.dp)
        ){

            item {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.clickable {
                        articleScreenViewModel.uploadFakeArticle()
                    }
                )
            }

            item {
                ImageContainer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .border(
                            width = 2.dp, shape = RoundedCornerShape(30.dp),
                            color = MaterialTheme.colorScheme.tertiary
                        ),
                    imageRes = R.drawable.ic_launcher_background
                )
            }

            item {

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium
                )

            }

        }



        /*Spacer(
            modifier = Modifier.height(16.dp)
        )

        Box(
            modifier = Modifier.weight(1f)
        ) {

        }*/


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
    imageRes: Int
) {
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier.fillMaxSize() ,
            painter = painterResource(imageRes),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Article Image"
        )
    }

}

