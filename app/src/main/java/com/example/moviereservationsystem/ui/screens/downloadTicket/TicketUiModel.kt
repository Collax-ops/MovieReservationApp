package com.example.moviereservationsystem.ui.screens.downloadTicket

data class TicketUiModel(
    val movieTitle: String,
    val userName: String,
    val date: String,
    val showTime: String,
    val seats: List<String>,
    val ticketPrice: Double,
    val totalPrice: Double
)
