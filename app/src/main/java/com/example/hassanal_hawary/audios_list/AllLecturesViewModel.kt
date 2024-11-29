package com.example.hassanal_hawary.audios_list

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

class AllLecturesViewModel : ViewModel() {


    private val storageDb = Firebase.storage

    private val _lecturesState = MutableStateFlow(AllLecturesState())
    val lecturesState = _lecturesState.asStateFlow()


    fun fetchAllLectures() {
        viewModelScope.launch {
            _lecturesState.update {
                it.copy(showProgress = true)
            }
        }


        /*

         */


        val storageRef = storageDb.reference.child("lectures")
//        storageRef.child("")
//        val audioPath = storageRef.child("1.m4a")
//        storageRef.listAll()

        val lectures = mutableListOf<String>()
        storageRef.listAll().addOnSuccessListener {
            val allFiles = it.items
            allFiles.forEach { subStorageRef ->
                val fileName = subStorageRef.name
                Log.d("LecturesViewModel", "fetchAllLectures: name: $fileName")
                lectures.add(fileName)
            }
            _lecturesState.update { state ->
                state.copy(
                    showProgress = false,
                    lectures = lectures.map { lectureName ->
                        Lecture(lectureTitle = lectureName)
                    }
                )
            }

            
        }.addOnFailureListener {

            Log.d("LecturesViewModel", "fetchAllLectures: fail with msg: ${it.message}")
        }

        val localFile = File.createTempFile(
            "audios", "m4a"
        )
        /*audioPath.getFile(localFile)
            .addOnSuccessListener { task ->
                Log.d("LecturesViewModel", "fetchAllLectures: success $task.bytesTransferred")

                val mediaPlayer = MediaPlayer()
                try {
                    mediaPlayer.setDataSource(localFile.path)
                    mediaPlayer.prepare()
                    mediaPlayer.start()

                } catch (e: Exception) {
                    Log.d("AllLectureViewModel", "fetchAllLectures: ${e.message}")
                }
                _lecturesState.update {
                    it.copy(showProgress = false)
                }

            }
            .addOnFailureListener { e ->
                Log.d("LecturesViewModel", "fetchAllLectures: fail ${e.message}")
                _lecturesState.update {
                    it.copy(showProgress = false)
                }
            }

*/

    }

}