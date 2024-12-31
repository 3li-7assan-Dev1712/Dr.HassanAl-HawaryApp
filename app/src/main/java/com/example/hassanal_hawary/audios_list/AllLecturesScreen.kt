package com.example.hassanal_hawary.audios_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
    modifier: Modifier = Modifier,
    onLectureClick: (String) -> Unit
) {

    val viewModel = viewModel<AllLecturesViewModel>()
    val state = viewModel.lecturesState.collectAsState()


    if (state.value.lectures.isEmpty())
        viewModel.fetchAllLectures()

    if (state.value.showProgress) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            /*
            it's time to get to sleep
             */
            CircularProgressIndicator()
        }
    }

    if (state.value.errorMessage != null) {
        Text(
            text = state.value.errorMessage!!,
            fontSize = 33.sp,
 
        )
    }

    if (state.value.lectures.isNotEmpty()) {
        LazyColumn {

            items(count = state.value.lectures.size) {
                val lec = state.value.lectures[it]
                LectureItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp, horizontal = 15.dp),
                    lecture = lec
                ) { lecName ->
                    onLectureClick(lecName)
                }
            }

        }
    }


}



/*
OK I am going to work on my dream
look at you now while you are typing
you are typing very good that's because you practiced doing
that
 */


@Composable
fun LectureItem(
    modifier: Modifier = Modifier,
    lecture: Lecture = Lecture(),
    onLectureClick: (String) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = lecture.lectureTitle,

            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.tertiary,
                    shape = RoundedCornerShape(16.dp)

                )
                .clickable {
                     onLectureClick(lecture.lectureTitle)
                }
                .padding(32.dp)
                .fillMaxWidth()
        )

    }
}


