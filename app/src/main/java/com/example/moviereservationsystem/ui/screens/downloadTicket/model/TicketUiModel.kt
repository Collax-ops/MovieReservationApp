package com.example.moviereservationsystem.ui.screens.downloadTicket.model

data class TicketUiModel(
    val movieTitle: String,
    val userName: String,
    val date: String,
    val showTime: String,
    val seats: List<String>,
    val totalPrice: Double
)