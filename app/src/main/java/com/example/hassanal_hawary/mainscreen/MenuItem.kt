package com.example.hassanal_hawary.mainscreen

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hassanal_hawary.R

data class MenuItem(
    val ImageVect: ImageVector,
    @StringRes val source: Int
)


val menusItems = listOf(
    MenuItem(Icons.Default.Home, R.string.main_screen),
    MenuItem(Icons.Default.Search, R.string.favorites),
    MenuItem(Icons.Default.Star, R.string.profile)
)
val programs = listOf(
    "المقــــالات",
    "الفــــتاوى",
    "الفيديوهات",
    "المحـــاضرات"
)