package com.example.hassanal_hawary.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hassanal_hawary.mainscreen.MenuItem
import com.example.hassanal_hawary.mainscreen.menusItems
import com.example.hassanal_hawary.ui.theme.Blue40


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
                contentDescription = stringResource(menuItem.source),
                modifier = Modifier.size(20.dp)

            )
        }

        Text(
            text = stringResource(menuItem.source),
            style = MaterialTheme.typography.bodyMedium
        )

    }
}


@Preview(name = "Bottom Menu", widthDp = 340, heightDp = 100)
@Composable
private fun BottomMenuPrev() {
    BottomMenu(
        modifier = Modifier.fillMaxSize(),
        menuItems = menusItems
    ) {

    }
}