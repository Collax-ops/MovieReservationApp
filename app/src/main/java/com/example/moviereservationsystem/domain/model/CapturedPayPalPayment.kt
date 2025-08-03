package com.example.moviereservationsystem.domain.model

import kotlinx.datetime.LocalDateTime

data class CapturedPayPalPayment(
    val orderId: String,
    val captureId: String,
    val amount: Double,
    val currency: String,
    val status: String,
    val transactionDate: LocalDateTime
)
