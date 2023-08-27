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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            modifier = Modifier.weight(1f),
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
                    MainScreenGridItem(programType = program)
                }

            }
        }
        BottomMenu(onItemClick = {
            onItemClick(it)
        })


    }




}

@Composable
fun MainScreenGridItem(
    programType: String
) {
    Box(
        modifier = Modifier
            .height(156.dp)
            .background(
                color = Blue40,
                shape = RoundedCornerShape(size = 32.dp)
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = programType,
            fontSize = 22.sp
        )
    }
}

@Preview(widthDp = 320)
@Composable
fun MainScreenGridItemPreview() {
    MainScreenGridItem(programType = "المقـــــالات")
}


@Composable
fun BottomMenu(
    modifier: Modifier = Modifier,
    menuItems: List<MenuItem> = listOf(),
    initialSelectedItemIndex: Int = 0,
    onItemClick: (Int) -> Unit
) {

    var currentSelectedItem by remember {
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