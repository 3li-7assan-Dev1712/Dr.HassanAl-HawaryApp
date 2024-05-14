package com.example.hassanal_hawary.presentation.all_articles

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hassanal_hawary.domain.model.Article
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AllArticlesViewModel : ViewModel() {


    private val firestoreDb = Firebase.firestore
    private val _articlesState = MutableStateFlow(AllArticlesState())
    val articlesState = _articlesState.asStateFlow()

    fun fetchAllArticles() {
        viewModelScope.launch {
            _articlesState.update {
                it.copy(showProgress = true)
            }
        }
        val allArts: MutableList<Article> = mutableListOf()
        firestoreDb.collection("articles")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    try {
                        Log.d("Ali Has", "fetchAllArticles: Error")
                        val article = Article(doc.get("title").toString(), doc.get("content").toString())
                        allArts.add(article)
                    } catch (e: Exception) {
                        _articlesState.update {
                            it.copy(
                                errorMessage = "Error man!"
                            )

                        }
                        Log.d("Ali Has", "fetchAllArticles: Error")
                    }
                }
                viewModelScope.launch {

                    _articlesState.update {
                        it.copy(
                            showProgress = false,
                            errorMessage = null,
                            articles = allArts
                        )
                    }
                }
            }
            .addOnFailureListener { exception ->
                viewModelScope.launch {
                    _articlesState.update {
                        it.copy(
                            showProgress = false,
                            errorMessage = exception.message,
                        )
                    }
                }
            }
    }



}