package com.example.moviereservationsystem.ui.screens.signup

data class SignUpUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSignUpEnabled: Boolean = false,
)
