package com.example.hassanal_hawary.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hassanal_hawary.ui.theme.Blue40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier,
    programs: List<String> = listOf(),
    navController: NavController,
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(156.dp)
                        .background(
                            color = Blue40,
                            shape = RoundedCornerShape(size = 32.dp)
                        ),
                    gridItemIndex = it,
                    contentAlignment = Alignment.BottomCenter

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
    modifier: Modifier,
    contentAlignment: Alignment,
    gridItemIndex: Int = 0,
    onGridItemClick: (Int) -> Unit
) {
    Box(
        modifier = modifier
            .padding(vertical = 30.dp)
            .clickable {
                onGridItemClick(gridItemIndex)
            },
        contentAlignment = contentAlignment
    ) {
        Text(
            text = programType,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(widthDp = 320)
@Composable
fun MainScreenGridItemPreview() {
    MainScreenGridItem(
        programType = "المقـــــالات",
        modifier = Modifier
            .fillMaxWidth()
            .height(156.dp)
            .background(
                color = Blue40,
                shape = RoundedCornerShape(size = 32.dp)
            ),
        contentAlignment = Alignment.BottomCenter

    ) {

    }
}


@Composable
fun BottomMenu(
    modifier: Modifier = Modifier,
    menuItems: List<MenuItem> = listOf(),
    initialSelectedItemIndex: Int = 0,
    onItemClick: (Int) -> Unit
) {

    var currentSelectedItem by rememberSaveable {
        mutableStateOf(initialSelectedItemIndex)
    }

    Row(
        modifier = modifier

            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        menuItems.forEachIndexed { index, item ->
            BottomMenuItem(
                menuItem = item,
                isSelected = currentSelectedItem == index,
                onMenuSelected = {
                    currentSelectedItem = index
                    onItemClick(index)
                }
            )
        }
    }

}


@Composable
fun BottomMenuItem(
    menuItem: MenuItem,
    isSelected: Boolean,
    onMenuSelected: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onMenuSelected()
        }

    ) {

        /*
        This is an active online editor to build this app
         */
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) Blue40 else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                imageVector = menuItem.ImageVect,
                contentDescription = menuItem.title,
                modifier = Modifier.size(20.dp)

            )
        }

        Text(
            text = menuItem.title,
            style = MaterialTheme.typography.bodyMedium
        )

    }
}