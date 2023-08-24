package com.example.hassanal_hawary.common

sealed class LoginRegisterProviderElement {
    object GoogleElement: LoginRegisterProviderElement()
    object FacebookElement: LoginRegisterProviderElement()
    object TelegramElement: LoginRegisterProviderElement()
}