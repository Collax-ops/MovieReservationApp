package com.example.moviereservationsystem.data.repository

import com.example.moviereservationsystem.data.local.dao.SeatDao
import com.example.moviereservationsystem.data.mapper.toDomain
import com.example.moviereservationsystem.domain.model.Seats
import com.example.moviereservationsystem.domain.repository.SeatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SeatRepositoryImpl @Inject constructor(
    private val seatDao: SeatDao,

) : SeatRepository {

    override fun observeSeats(theaterId: Int): Flow<List<Seats>> =
        seatDao.observeSeats(theaterId)
            .map { list -> list.map { it.toDomain() } }

    override suspend fun toggleAvailability(seatId: String) {
        val entity = seatDao.getSeatById(seatId)
        seatDao.updateAvailability(seatId, !entity.isAvailable)
    }
}