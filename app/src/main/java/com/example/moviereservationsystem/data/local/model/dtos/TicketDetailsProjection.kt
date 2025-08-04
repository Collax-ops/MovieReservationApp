package com.example.moviereservationsystem.data.local.model.dtos

data class TicketDetailsProjection(
    val ticketId: Int,
    val totalPrice: Double,
    val purchaseDate: String,
    val movieTitle: String,
    val showStartTime: String,
    val showEndTime: String,
    val seatList: String
)
