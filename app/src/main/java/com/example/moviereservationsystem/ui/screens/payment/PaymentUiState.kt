package com.example.moviereservationsystem.ui.screens.payment

data class PaymentUiState(
    val selectedMethod: PaymentMethod = PaymentMethod.NONE,
    val isLoading: Boolean = false,
    val orderId: String? = null,
    val errorMessage: String? = null
)