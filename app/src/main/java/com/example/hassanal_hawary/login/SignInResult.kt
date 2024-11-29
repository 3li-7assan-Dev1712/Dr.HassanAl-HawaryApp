package com.example.hassanal_hawary.login

data class SignInResult (
    val data: UserData? = null,
    val errorMessage: String? = null
)

data class UserData(
    val userId: String,
    val username: String?,
    val userProfilePictureUrl: String?
)
