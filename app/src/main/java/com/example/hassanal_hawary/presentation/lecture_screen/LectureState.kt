package com.example.hassanal_hawary.presentation.lecture_screen

data class LectureState(
    val showProgress: Boolean = true,
    val trackPosition: Float = 0.0f,
    val play: Boolean = false,
    val error: Boolean = false
)
