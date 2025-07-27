package com.example.moviereservationsystem.ui.screens.seat

import com.example.moviereservationsystem.ui.screens.seat.model.SeatUiState

data class SeatBookingUiState(
    val seats: List<SeatUiState> = emptyList(),
    val selectedCount: Int = 0
)
