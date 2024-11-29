package com.example.hassanal_hawary.register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


/*
This view model will handle the functionality of signup
for now email and password will be used for signup as well
as google and facebook.
 */

class SignUpViewModel: ViewModel(
) {

    private val _signupState = MutableStateFlow(SignUpState())

    val signupState = _signupState.asStateFlow()

    private var auth: FirebaseAuth

    init {

        auth  = Firebase.auth

    }

    fun signUp(email: String, password: String) {
        // validate the email and password
        if (email.isEmpty()) {
            _signupState.update {
                it.copy(
                    emailError = true
                )
            }
        } else if (password.isEmpty()) {
            _signupState.update {
                it.copy(
                    passwordError = true
                )
            }
        } else {

            /*
            here we are going to create a new
            user with email and password yaah!
            I'm really excited about that *_*
             */

            auth.createUserWithEmailAndPassword(
                email,
                password
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    if (user != null) {
                        Log.d("SignUpViewModel: ", "signUp: Created the user successfully" +
                                "user email: ${user.email} \n" +
                                "user id: ${user.uid}")
                    }
                }
            }.addOnFailureListener{
                Log.d("SignUpViewModel", "signUp: faild with msg: ${it.message}")
            }


        }
    }

    fun emailChanged(newEmail: String) {
        _signupState.update {
            it.copy(
                email = newEmail
            )
        }
    }
    fun passwordChanged(newPassword: String) {
        _signupState.update {
            it.copy(
                password = newPassword
            )
        }
    }



}