package com.example.hassanal_hawary.presentation.article_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import com.example.hassanal_hawary.R

/*
In this screen the articles will be  visible so that the user can easily
navigate from one article to another to read and share them
 */
@Composable
fun ArticleScreen() {

    Column (
        modifier = Modifier.fillMaxSize()
    ){

        // title text
        Text(
            text = "التعامل مع الجثث المحروقة",
            style = MaterialTheme.typography.headlineLarge
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
                text = "التعامل مع الحثث المحروقة امر لابد من الاهتمام به" +
                        "ولذلك كتب هده المقالة حتى يستفيد منها المواطنون ويهتم بها المسؤولون" +
                        "اقول وبالله بالتوفيق" +
                        "1 لابد من ببنيبنيتبن" +
                        "يبيبنيت" +
                        "يباينبي " +
                        "يبنيت",
                style = MaterialTheme.typography.displayMedium
            )
        }


    }
}

@Preview(widthDp = 320, heightDp = 720)
@Composable
fun ArticleScreenPreview() {

    ArticleScreen()
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
            painter = painterResource(imageRes),
            contentDescription = "Article Image"
        )
    }

}

