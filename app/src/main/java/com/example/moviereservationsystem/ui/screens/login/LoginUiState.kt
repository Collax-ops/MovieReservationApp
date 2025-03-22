package com.example.moviereservationsystem.ui.screens.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoginEnabled: Boolean = false,
    val isLoading: Boolean = false
)
