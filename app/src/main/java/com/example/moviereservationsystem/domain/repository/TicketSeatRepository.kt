package com.example.moviereservationsystem.domain.repository

import com.example.moviereservationsystem.domain.model.TicketSeat


interface TicketSeatRepository {
    suspend fun insertTicketSeats(refs: List<TicketSeat>)
}