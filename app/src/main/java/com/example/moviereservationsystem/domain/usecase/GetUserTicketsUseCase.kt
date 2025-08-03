package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.repository.TicketRepository
import javax.inject.Inject

class GetUserTicketsUseCase @Inject constructor(
    private val ticketRepository: TicketRepository
) {
    operator fun invoke(userId: Int) = ticketRepository.getUserTickets(userId)
}