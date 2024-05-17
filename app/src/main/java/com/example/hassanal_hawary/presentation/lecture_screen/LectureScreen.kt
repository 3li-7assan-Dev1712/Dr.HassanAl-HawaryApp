package com.example.hassanal_hawary.presentation.lecture_screen

import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView.ScaleType
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

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

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {


        if (lectureState.showProgress) {
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp)
            )
        }

        if (lectureState.play) {
            Image(

                imageVector = Icons.Default.Place,
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
        }
        if (lectureState.error) {
            Image(

                imageVector = Icons.Default.Warning,
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
        }


    }


}