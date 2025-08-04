package com.example.moviereservationsystem.domain.model

data class BookingHistory(
    val ticketId: Int,
    val scheduleId: Int,
    val totalPrice: Double,
    val purchaseDate: String,
    val movieId: Int,
    val movieTitle: String,
    val seatCount: Int
)
