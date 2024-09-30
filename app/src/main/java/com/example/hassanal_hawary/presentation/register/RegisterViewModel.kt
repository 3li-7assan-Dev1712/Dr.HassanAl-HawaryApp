package com.example.hassanal_hawary.presentation.register

import androidx.lifecycle.ViewModel
import com.example.hassanal_hawary.presentation.sign_in.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


/*
This view model will handle the functionality of signup
for now email and password will be used for signup as well
as google and facebook.
 */

class RegisterViewModel: ViewModel(
) {

    private val _signupState = MutableStateFlow(SignUpState())

    val signupState = _signupState.asStateFlow()


    fun register(email: String, password: String) {
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

        }
    }



}