package com.example.moviereservationsystem.data.repository

import com.example.moviereservationsystem.data.local.dao.TicketDao
import com.example.moviereservationsystem.data.mapper.toDomain
import com.example.moviereservationsystem.data.mapper.toEntity
import com.example.moviereservationsystem.domain.model.BookingHistory
import com.example.moviereservationsystem.domain.model.Ticket
import com.example.moviereservationsystem.domain.model.TicketDetail
import com.example.moviereservationsystem.domain.repository.TicketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject

class TicketRepositoryImpl @Inject constructor(
    private val ticketDao: TicketDao,
) : TicketRepository{
    override suspend fun saveTicket(ticket: Ticket) : Long {
        return ticketDao.insertTicket(ticket.toEntity())
    }

    override fun getOccupiedSeats(scheduleId: Int): Flow<List<String>> {
        return ticketDao.getOccupiedSeats(scheduleId)
    }

    override fun getBookingHistory(userId: String): Flow<List<BookingHistory>> {
        return ticketDao.getBookingHistory(userId).map { tickets ->
            tickets.map { it.toDomain() }
        }
    }

    override suspend fun getTicketDetails(
        ticketId: Int,
        userName: String
    ): TicketDetail {
        val projection = ticketDao.getTicketDetails(ticketId)
        return projection.toDomain(userName)
    }
}