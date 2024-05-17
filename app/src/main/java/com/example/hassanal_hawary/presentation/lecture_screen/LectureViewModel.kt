package com.example.hassanal_hawary.presentation.lecture_screen

import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.File

class LectureViewModel : ViewModel() {

    private val _lectureState = MutableStateFlow(LectureState())

    val lectureState = _lectureState.asStateFlow()

    val mStorage = Firebase.storage

    fun downloadAndPlayAudio(
        audioName: String
    ) {
        val fileToDownloadRef = mStorage.reference.child("lectures/$audioName")
        val localFile = File.createTempFile(
            "audios", "mp3"
        )
        fileToDownloadRef.getFile(localFile)
            .addOnSuccessListener { task ->
                Log.d("LecturesViewModel", "fetchAllLectures: success $task.bytesTransferred")

            }
            .addOnCompleteListener {
                val mediaPlayer = MediaPlayer()
                try {
                    mediaPlayer.setDataSource(localFile.path)
                    mediaPlayer.prepare()
                    mediaPlayer.start()

                } catch (e: Exception) {
                    Log.d("AllLectureViewModel", "fetchAllLectures: ${e.message}")
                }
                _lectureState.update {
                    it.copy(
                        showProgress = false,
                        error = false,
                        play = true
                    )
                }
            }
            .addOnFailureListener { e ->
                Log.d("LecturesViewModel", "fetchAllLectures: fail ${e.message}")
                _lectureState.update {
                    it.copy(
                        showProgress = false,
                        error = true,
                        play = false
                    )
                }
            }


    }
}