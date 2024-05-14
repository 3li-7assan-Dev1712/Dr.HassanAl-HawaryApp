package com.example.hassanal_hawary.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _stateFlow = MutableStateFlow(0)
    
    val stateFlow = _stateFlow.asStateFlow()

    private val _bottomBarState = mutableStateOf(false)
    val bottomBarState = _bottomBarState


    /*
    This is called cold flow because it will not work if we don't collect it
    on the other hand the hot flow will be called even if we don't collect it

    the hot flow is the state flow which is similar to live data
     */
    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(currentValue)
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    private fun collectFlow() {
        viewModelScope.launch {
            countDownFlow
                .filter { time ->
                    time % 2 == 0
                }
                .map { time ->
                    time * time
                }
                .onEach { time->
                    println("time")
                }
                .count {
                    it % 2 == 0
                }
        }
    }

    fun userClickItem(itemIndex: Int) {

    }
    fun newNavigation(rout: String) {
        when (rout) {
            "sign_in", "all_articles_screen", "register" -> {
                _bottomBarState.value = false
            }

            else -> {
                _bottomBarState.value = true
            }

        }
    }
}