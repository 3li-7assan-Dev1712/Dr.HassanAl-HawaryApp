package com.example.hassanal_hawary.presentation.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(): ViewModel()
{
    private val _state = MutableStateFlow(SignInState())

    val state = _state.asStateFlow()

    fun onSignInResult(signInResult: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = signInResult.data != null,
                errorMessage = signInResult.errorMessage
            )
        }
    }
    fun resetState() {
        _state.update {
            SignInState()
        }
    }

    fun userClickSignInBtn() {
        if (_state.value.enteredEmail.isBlank()) {
            _state.update {
                it.copy(
                    enterValidEmailMsg =
                    "Please enter a vaild email"
                )
            }
        } else if (_state.value.enteredPassword.isBlank()) {
            _state.update {
                it.copy(
                    enterValidPassowrdMsg =
                    "Please enter a vaild password"
                )
            }
        } else {

        }
    }

    fun emailChanged(email: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(enteredEmail = email)
            }
        }

    }
    fun passwordChanged(password: String) {

        viewModelScope.launch {
            _state.update {
                it.copy(enteredPassword = password)
            }
        }
    }
}