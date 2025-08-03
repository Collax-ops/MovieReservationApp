package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.model.TicketSeat
import com.example.moviereservationsystem.domain.repository.TicketSeatRepository
import javax.inject.Inject

class InsertTicketSeatsUseCase @Inject constructor(
    private val ticketSeatRepository: TicketSeatRepository
) {
    suspend operator fun invoke(refs: List<TicketSeat>) {
        ticketSeatRepository.insertTicketSeats(refs)
    }
}