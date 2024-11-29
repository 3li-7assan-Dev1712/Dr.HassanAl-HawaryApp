package com.example.hassanal_hawary.register

data  class SignUpState (
    val email: String = "",
    val emailError: Boolean = false,
    val password: String = "",
    val passwordError: Boolean = false,
    val showProgress: Boolean = false
)