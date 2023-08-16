package com.example.hassanal_hawary.presentation.sign_in

data class SignInResult (
    val data: UserData? = null,
    val errorMessage: String? = null
)

data class UserData(
    val userId: String,
    val username: String?,
    val userProfilePictureUrl: String?
)
