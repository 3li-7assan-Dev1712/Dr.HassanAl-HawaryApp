package com.example.hassanal_hawary.presentation.profile

import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.hassanal_hawary.domain.model.Article
import com.example.hassanal_hawary.presentation.sign_in.UserData
import com.google.android.gms.common.internal.ImagesContract
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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
                // show user image 
               /* Text(
                    text = "${userData.username.get(0)}" ,
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold
                    )*/
                if (!userData.userProfilePictureUrl.isNullOrBlank()) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model =userData.userProfilePictureUrl,
                        contentDescription = "Profile picture",
                    )
                }
            }
            Text(
                text = userData.username,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.weight(1f))


        Button(
            onClick = {
                val art_1 = Article("Title 1", "Any content")
                val art_2 = Article("Title 2", "Any content 3iej 3")
                val art_3 = Article("Title 3", "Any content dkjfd 1245")
                val art_4 = Article("Title 4", "Any content fdkfj")
                val art_5 = Article("Title 5", "Any content fjdkfj3")
                Firebase.firestore.collection("articles")
                    .add(art_5)
                Firebase.firestore.collection("articles")
                    .add(art_4)

                Firebase.firestore.collection("articles")
                    .add(art_3)

                Firebase.firestore.collection("articles")
                    .add(art_2)

                Firebase.firestore.collection("articles")
                    .add(art_1)




            }
        ) {
            Text(
                text = "upload fake data"
            )
        }

        Button(onClick = {
            onSignOutClick()
        }) {
            Text(
                text = "Sign out"
            )
        }
    }

}