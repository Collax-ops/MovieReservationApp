package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.repository.TicketRepository
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject

class GetOccupiedSeatsUseCase @Inject constructor(
    private val ticketRepository: TicketRepository
) {
    operator fun invoke(scheduleId: Int) =
        ticketRepository.getOccupiedSeats(scheduleId)
}