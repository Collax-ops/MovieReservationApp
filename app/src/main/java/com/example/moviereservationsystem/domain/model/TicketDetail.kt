package com.example.moviereservationsystem.domain.model

data class TicketDetail (
    val movieTitle: String,
    val userName: String,
    val date: String,
    val showTime: String,
    val seats: List<String>,
    val totalPrice: Double
)