package com.example.hassanal_hawary.presentation.lecture_screen

import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File

class LectureViewModel : ViewModel() {

    private val _lectureState = MutableStateFlow(LectureState())

    val lectureState = _lectureState.asStateFlow()

    val mStorage = Firebase.storage

    private val mPlayer = MediaPlayer()

//    private val _progress = state

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
                try {
                    mPlayer.setDataSource(localFile.path)
                    mPlayer.prepare()
                    mPlayer.start()
                    mPlayer.duration

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

    fun playPauseClick(play: Boolean) {

        if (play)
            mPlayer.start()
        else
            mPlayer.pause()


        viewModelScope.launch {

            _lectureState.update {

                it.copy(play = !play)
            }
        }


    }
    fun rollbackClick() {
        try {
            mPlayer.seekTo(mPlayer.currentPosition - 15000)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun forwardClick() {
        try {
            mPlayer.seekTo(mPlayer.currentPosition + 15000)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
