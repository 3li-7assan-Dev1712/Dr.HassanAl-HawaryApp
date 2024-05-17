package com.example.hassanal_hawary.presentation.all_lectures

import com.example.hassanal_hawary.domain.model.Article

data class AllLecturesState(
    val showProgress: Boolean = true,
    val errorMessage: String? = null,
    val lectures: List<Lecture> = listOf()
)
