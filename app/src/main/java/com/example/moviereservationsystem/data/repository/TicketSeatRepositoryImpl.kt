package com.example.moviereservationsystem.data.repository

import com.example.moviereservationsystem.data.local.dao.TicketSeatDao
import com.example.moviereservationsystem.data.mapper.toEntity
import com.example.moviereservationsystem.domain.model.TicketSeat
import com.example.moviereservationsystem.domain.repository.TicketSeatRepository
import javax.inject.Inject

class TicketSeatRepositoryImpl @Inject constructor(
    private val ticketSeatDao: TicketSeatDao
) : TicketSeatRepository {
    override suspend fun insertTicketSeats(refs: List<TicketSeat>) {
        ticketSeatDao.insertTicketSeats(refs.map { it.toEntity() })
    }
}