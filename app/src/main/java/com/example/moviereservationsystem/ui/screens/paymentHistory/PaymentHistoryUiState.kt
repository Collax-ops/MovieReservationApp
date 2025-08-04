package com.example.moviereservationsystem.ui.screens.paymentHistory

import com.example.moviereservationsystem.ui.screens.paymentHistory.model.PaymentHistoryUiModel

data class PaymentHistoryUiState(
    val history: List<PaymentHistoryUiModel> = emptyList()
)
