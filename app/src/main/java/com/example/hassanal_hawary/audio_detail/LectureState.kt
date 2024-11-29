package com.example.hassanal_hawary.audio_detail

data class LectureState(
    val showProgress: Boolean = true,
    val trackPosition: Float = 0.0f,
    val play: Boolean = false,
    val error: Boolean = false
)
