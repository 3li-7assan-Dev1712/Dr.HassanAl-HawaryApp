package com.example.hassanal_hawary.presentation.main_screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val ImageVect: ImageVector,
    val title: String
)


val menusItems = listOf(
    MenuItem(Icons.Default.Home, "Home Screen"),
    MenuItem(Icons.Default.Search, "Search"),
    MenuItem(Icons.Default.Star, "Favorites"),
    MenuItem(Icons.Default.Person, "Profile")
)
val programs = listOf(
    "المقــــالات",
    "الفــــتاوى",
    "الفيديوهات",
    "المحـــاضرات",
    "نبذة عن الشيخ"
)