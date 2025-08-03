package com.example.moviereservationsystem.domain.model

import kotlinx.datetime.LocalDateTime

data class Payment (
    val paymentId: Int = 0,
    val ticketId: Int,
    val amount: Double,
    val paymentMethod: String,
    val paymentStatus: String,
    val transactionDate: LocalDateTime
)