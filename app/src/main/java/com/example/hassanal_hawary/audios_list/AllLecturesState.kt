package com.example.hassanal_hawary.audios_list

data class AllLecturesState(
    val showProgress: Boolean = true,
    val errorMessage: String? = null,
    val lectures: List<Lecture> = listOf()
)
