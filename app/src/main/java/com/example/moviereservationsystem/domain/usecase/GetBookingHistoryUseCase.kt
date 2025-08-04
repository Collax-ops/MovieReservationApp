package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.repository.TicketRepository
import javax.inject.Inject

class GetBookingHistoryUseCase @Inject constructor(
    private val ticketRepository: TicketRepository
) {
    operator fun invoke(userId: String) = ticketRepository.getBookingHistory(userId)
}