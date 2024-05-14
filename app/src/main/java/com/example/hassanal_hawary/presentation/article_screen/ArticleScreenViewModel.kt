package com.example.hassanal_hawary.presentation.article_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ArticleScreenViewModel: ViewModel() {

    val database = Firebase.firestore

    fun getArticle() {
        database.collection("articles")
            .get()
            .addOnSuccessListener { result ->
                for (art in result) {
                    val art = art.toObject(Article::class.java)

                }
            }
    }

    fun uploadFakeArticle()  {
        val art = Article(
            title = "Test title",
            imageUrl = "j",
            content = "ANy content just to show on the screen and check " +
                    "if the firestore is working or not ")
        database.collection("articles")
            .add(art)
            .addOnSuccessListener {
                Log.d("ArticleViewModel", "uploadFakeArticle: success")
            }
            .addOnFailureListener {
                Log.d("ArticleViewModel", "uploadFakeArticle: fail")
            }
    }






}