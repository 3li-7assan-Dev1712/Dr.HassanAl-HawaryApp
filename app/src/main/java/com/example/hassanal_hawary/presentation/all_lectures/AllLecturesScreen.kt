package com.example.hassanal_hawary.presentation.all_lectures

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


/*
this will show the lectures of Dr hassan app
 */
@Composable
fun AllLecturesScreen(
    modifier: Modifier = Modifier
) {

    val viewModel = viewModel<AllLecturesViewModel>()
    val state = viewModel.lecturesState.collectAsState()


    viewModel.fetchAllLectures()

    if (state.value.showProgress) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            CircularProgressIndicator()
        }
    }

    if (state.value.errorMessage != null) {
        Text(
            text = state.value.errorMessage!!,
            fontSize = 33.sp,
            color = Color.Red
        )
    }

    if (state.value.lectures.isNotEmpty()) {
        LazyColumn {

            items (count = state.value.lectures.size){
                val lec = state.value.lectures[it]
                Text(
                    text = lec.lectureTitle,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.tertiary,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .fillMaxWidth()
                        .padding(vertical = 15.dp),
                    textAlign = TextAlign.Center


                )
            }

        }
    }


}

@Composable
fun LectureItem(
    modifier: Modifier = Modifier,
    lecture: Lecture = Lecture()
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = lecture.lectureTitle,
            style = MaterialTheme.typography.bodyLarge
        )

    }
}


