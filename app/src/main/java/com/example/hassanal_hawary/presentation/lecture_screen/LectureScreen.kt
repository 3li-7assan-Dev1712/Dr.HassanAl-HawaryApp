package com.example.hassanal_hawary.presentation.lecture_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hassanal_hawary.R

@Composable
fun LectureScreen(

    modifier: Modifier = Modifier,
    lectureName: String = ""
) {

    val lectureViewModel = viewModel<LectureViewModel>()
    val lectureState by lectureViewModel.lectureState.collectAsState()

    LaunchedEffect(key1 = true) {
        Log.d("Ali Hassan", "LectureScreen: call")
        lectureViewModel.downloadAndPlayAudio(lectureName)
    }

    if (lectureState.showProgress) {

        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {

            CircularProgressIndicator(
                modifier = Modifier.size(64.dp)
            )

        }
    }


    if(lectureState.showProgress)
        return

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        AudioProgress(lectureName = "التعامل مع الجثث المحروقة")


        Box(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {

            AudioControllerSection(
                modifier = Modifier.height(45.dp),
                onRollbackClick = {


                },
                onForwardClick = {

                },
                playPause = lectureState.play,
                onPlayPauseClick = { play ->
                    lectureViewModel.playPauseClick(play)
                })

        }
    }


}


@Composable
fun AudioProgress(
    modifier: Modifier = Modifier,
    lectureName: String


) {


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.size(200.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.dr_hassan_logo),
                contentDescription = "Hassan Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )


        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = lectureName,
            style = MaterialTheme.typography.bodyLarge
        )


    }


}


@Preview(widthDp = 320, heightDp = 640)
@Composable
private fun LectureScreenPrev() {
    LectureScreen()
}

@Preview(widthDp = 320, heightDp = 400)
@Composable
private fun AudioProgressPreview() {
    AudioProgress(
        modifier = Modifier
            .fillMaxSize()
            .clip(
                CircleShape
            ),
        lectureName = "التعامل مع الجثث المحروقة"
    )
}

@Composable
fun AudioControllerSection(
    modifier: Modifier = Modifier,
    playPause: Boolean = true,
    onPlayPauseClick: (Boolean) -> Unit,
    onRollbackClick: () -> Unit,
    onForwardClick: () -> Unit

) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {


        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Image(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        onRollbackClick()
                    }
            )

            Image(
                imageVector = if (playPause) Icons.Default.PlayArrow else Icons.Default.ThumbUp,
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        onPlayPauseClick(playPause)
                    }
            )

            Image(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        onForwardClick()
                    }
            )


        }

    }

}

@Preview(widthDp = 320, heightDp = 400)
@Composable
private fun AudioControllerPrev() {


    AudioControllerSection(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        onPlayPauseClick = { play ->

        },
        onRollbackClick = {

        },
        onForwardClick = {

        }
    )

}