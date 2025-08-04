package com.example.moviereservationsystem.data.local.model.dtos

data class BookingHistoryProjection(
    val ticketId: Int,
    val scheduleId: Int,
    val totalPrice: Double,
    val purchaseDate: String,
    val movieId: Int,
    val movieTitle: String,
    val seatCount: Int
)
