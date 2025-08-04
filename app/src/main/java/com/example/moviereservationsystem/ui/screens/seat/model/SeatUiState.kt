package com.example.moviereservationsystem.ui.screens.seat.model

data class SeatUiState (
    val seatId: String,
    val row: Char,
    val number: Int,
    val isAvailable: Boolean,
    val isSelected: Boolean
)