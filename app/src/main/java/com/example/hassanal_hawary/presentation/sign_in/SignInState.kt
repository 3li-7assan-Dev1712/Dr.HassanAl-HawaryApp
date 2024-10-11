package com.example.hassanal_hawary.presentation.sign_in

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val showSignInProgressBar: Boolean = false,
    val errorMessage: String? = null,
    val enteredEmail: String = "",
    val enteredPassword: String = "",
    val enterValidEmailMsg: String = "",
    val enterValidPassowrdMsg: String= "",
    val navigateTo: String? = null

    )
