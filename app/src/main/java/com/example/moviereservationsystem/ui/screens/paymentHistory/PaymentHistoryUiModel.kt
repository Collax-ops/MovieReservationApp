package com.example.moviereservationsystem.ui.screens.paymentHistory

import com.example.moviereservationsystem.R

data class PaymentHistoryUiModel(
    val id: String,
    val date: String,
    val amount: Double,
    val status: String,
    val methodIconRes: Int = R.drawable.paypal_icon
)
