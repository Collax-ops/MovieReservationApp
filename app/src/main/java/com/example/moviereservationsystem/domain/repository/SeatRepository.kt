package com.example.moviereservationsystem.domain.repository

import com.example.moviereservationsystem.domain.model.Seats
import kotlinx.coroutines.flow.Flow

interface SeatRepository {
    fun observeSeats(theaterId: Int): Flow<List<Seats>>
    suspend fun toggleAvailability(seatId: String)
}