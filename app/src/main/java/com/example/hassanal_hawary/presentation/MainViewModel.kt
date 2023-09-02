package com.example.hassanal_hawary.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){


    private val _bottomBarState = mutableStateOf(false)
    val bottomBarState = _bottomBarState

    fun newNavigation(rout: String) {
        when (rout) {
            "sign_in" -> {
                _bottomBarState.value = false
            }
            "register" -> {
                _bottomBarState.value = false
            }
            else -> {
                _bottomBarState.value = true
            }

        }
    }
}