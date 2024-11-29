package com.example.hassanal_hawary.articles_list

import com.example.hassanal_hawary.domain.model.Article

data class AllArticlesState(
    val showProgress: Boolean = true,
    val errorMessage: String? = null,
    val articles: List<Article> = listOf()
)
