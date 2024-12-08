package com.example.hassanal_hawary.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hassanal_hawary.R
import com.example.hassanal_hawary.ui.theme.Blue40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier,
    programs: List<String> = listOf(),
    onItemClick: (Int) -> Unit
) {


    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 7.5.dp
        )
    ) {
        items(count = programs.size) {
            val program = programs[it]
            Box(
                modifier = Modifier.padding(8.dp)

            ) {
                MainScreenGridItem(
                    programType = program,
                    gridItemIndex = it,

                    ) { itemIndex ->
                    onItemClick(itemIndex)
                }
            }

        }
    }


}

@Composable
fun MainScreenGridItem(
    programType: String,
    modifier: Modifier = Modifier,
    gridItemIndex: Int = 0,
    onGridItemClick: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier

            .clickable {
                onGridItemClick(gridItemIndex)
            }
            .clip(
                RoundedCornerShape(16.dp)
            )
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .height(156.dp),

        ) {


        Image(
            painter = when (gridItemIndex) {
                0 -> {
                    painterResource(R.drawable.article_logo)
                }
                1 -> {
                    painterResource(R.drawable.questions)
                }
                2 -> {
                    painterResource(R.drawable.video)
                }
                3 -> {
                    painterResource(R.drawable.headphones)
                }

                else -> {
                    painterResource(R.drawable.dr_hassan_logo)
                }
            },
            modifier = Modifier.size(45.dp),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = when(gridItemIndex) {
                0 -> stringResource(R.string.articles)
                1 -> stringResource(R.string.questions)
                2 -> stringResource(R.string.videos)
                3 -> stringResource(R.string.lectures)
                4 -> stringResource(R.string.summary)
                else -> "" },
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(widthDp = 320)
@Composable
fun MainScreenGridItemPreview() {
    MainScreenGridItem(
        programType = "المقـــــالات"

    ) {

    }
}



@Preview(name = "Main Screen", widthDp = 340, heightDp = 640)
@Composable
private fun MainScreenPrev() {


    MainScreen(
        modifier = Modifier.fillMaxSize(),
        programs = listOf("A", "L", "N", "G")
    ) {

    }

}
