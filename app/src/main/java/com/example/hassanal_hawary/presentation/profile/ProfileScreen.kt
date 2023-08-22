package com.example.hassanal_hawary.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hassanal_hawary.presentation.sign_in.UserData

@Composable
fun ProfileScreen(
    userData: UserData?,
    onSignOutClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (userData?.userProfilePictureUrl != null) {


            // show image
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (userData?.username != null) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(
                        shape = CircleShape,
                        color = Color.Magenta
                    ),
                contentAlignment = Alignment.Center,

            ) {
                Text(
                    text = "${userData.username.get(0)}" ,
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold
                    )
            }
            Text(
                text = userData.username,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            onSignOutClick()
        }) {
            Text(
                text = "Sign out"
            )
        }
    }

}