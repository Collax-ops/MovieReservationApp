package com.example.moviereservationsystem.data.repository

import com.example.moviereservationsystem.data.local.dao.TicketDao
import com.example.moviereservationsystem.data.mapper.toDomain
import com.example.moviereservationsystem.data.mapper.toEntity
import com.example.moviereservationsystem.domain.model.Ticket
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

    override fun getUserTickets(userId: Int): Flow<List<Ticket>> {
        return ticketDao.getUserTickets(userId).map { tickets ->
            tickets.map { it.toDomain() }
        }
    }
}