package com.example.moviereservationsystem.domain.repository

import com.example.moviereservationsystem.domain.model.Ticket
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime

interface TicketRepository {

    suspend fun saveTicket(ticket: Ticket) : Long

    fun getOccupiedSeats(scheduleId: Int): Flow<List<String>>

    fun getUserTickets(userId: Int) : Flow<List<Ticket>>
}

