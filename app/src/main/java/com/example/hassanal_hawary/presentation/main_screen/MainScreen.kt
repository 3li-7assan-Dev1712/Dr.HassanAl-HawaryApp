package com.example.hassanal_hawary.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hassanal_hawary.ui.theme.Blue40

@Composable
fun MainScreen(
    modifier: Modifier,
    navController: NavController
) {



}

@Composable
fun MainScreenGridItem(
    programType: String
) {
    Box(modifier = Modifier.height(156.dp)
        .background(
            color = Blue40,
            shape = RoundedCornerShape(size = 32.dp)
        ),
        contentAlignment = Alignment.BottomCenter) {
        Text(
            text = programType,
            fontSize = 22.sp
        )
    }
}

