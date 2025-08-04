package com.example.moviereservationsystem.domain.repository

import com.example.moviereservationsystem.domain.model.BookingHistory
import com.example.moviereservationsystem.domain.model.Ticket
import com.example.moviereservationsystem.domain.model.TicketDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime

interface TicketRepository {

    suspend fun saveTicket(ticket: Ticket) : Long

    fun getOccupiedSeats(scheduleId: Int): Flow<List<String>>

    fun getBookingHistory(userId: String) : Flow<List<BookingHistory>>

    suspend fun getTicketDetails(ticketId: Int, userName: String): TicketDetail
}

