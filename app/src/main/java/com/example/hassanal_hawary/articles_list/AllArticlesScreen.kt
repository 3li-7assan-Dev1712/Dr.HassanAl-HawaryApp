package com.example.hassanal_hawary.articles_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hassanal_hawary.R
import com.example.hassanal_hawary.domain.model.Article
import com.example.hassanal_hawary.favorites.Articles

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


@Composable
fun ArticleListItem(
    modifier: Modifier = Modifier,
    article: Article,
    articleIndex: Int = 0,
    onArticleClick: (Int) -> Unit
) {

    Column(
        modifier = modifier
            .border(
                border = BorderStroke(
                    width = 4.dp, color = Color.LightGray
                ),
                shape = RoundedCornerShape(size = 20.dp)
            )
            .padding(15.dp)
    ) {

        Text(
            text = article.content,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f),
            overflow = TextOverflow.Ellipsis
        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = LocalContext.current.getString(R.string.read_more),
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.clickable {
                    onArticleClick(articleIndex)
                }
            )
        }

    }


}



@Preview(widthDp = 320, heightDp = 200)
@Composable
private fun AllArticlesPrev() {

    val art = Article(
        title = "fj",
        content = "kdfjdkfjkd fdlsjf ds fdfjlkds fsljfldjflkdsk flk" +"kdfjdkfjkd fdlsjf ds fdfjlkds fsljfldjflkdsk flk" +"kdfjdkfjkd fdlsjf ds fdfjlkds fsljfldjflkdsk flk" +"kdfjdkfjkd fdlsjf ds fdfjlkds fsljfldjflkdsk flk" +"kdfjdkfjkd fdlsjf ds fdfjlkds fsljfldjflkdsk flk" +"kdfjdkfjkd fdlsjf ds fdfjlkds fsljfldjflkdsk flk" +"kdfjdkfjkd fdlsjf ds fdfjlkds fsljfldjflkdsk flk" +"kdfjdkfjkd fdlsjf ds fdfjlkds fsljfldjflkdsk flk" +"kdfjdkfjkd fdlsjf ds fdfjlkds fsljfldjflkdsk flk" +"kdfjdkfjkd fdlsjf ds fdfjlkds fsljfldjflkdsk flk" +"kdfjdkfjkd fdlsjf ds fdfjlkds fsljfldjflkdsk flk" +"kdfjdkfjkd fdlsjf ds fdfjlkds fsljfldjflkdsk flk" +"kdfjdkfjkd fdlsjf ds fdfjlkds fsljfldjflkdsk flk" +"kdfjdkfjkd fdlsjf ds fdfjlkds fsljfldjflkdsk flk" +
                ""
    )

    ArticleListItem(article = art) {

    }

}
