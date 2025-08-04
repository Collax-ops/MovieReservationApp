package com.example.moviereservationsystem.ui.screens.signup

data class SignUpUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isSignUpEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val navigateToLogin: Boolean = false
)
