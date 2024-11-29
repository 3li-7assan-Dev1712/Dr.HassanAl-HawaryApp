package com.example.hassanal_hawary.core

sealed class LoginRegisterProviderElement {
    object GoogleElement: LoginRegisterProviderElement()
    object FacebookElement: LoginRegisterProviderElement()
    object TelegramElement: LoginRegisterProviderElement()
}