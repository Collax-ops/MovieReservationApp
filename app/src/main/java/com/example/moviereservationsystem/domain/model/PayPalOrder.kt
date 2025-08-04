package com.example.moviereservationsystem.domain.model

data class PayPalOrder (
    val id: String,
    val status: String,
    val approvalLink: String
)