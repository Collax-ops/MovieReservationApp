package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.repository.TicketRepository
import javax.inject.Inject

class GetTicketDetailsUseCase @Inject constructor(
    private val ticketRepository: TicketRepository,
) {
    suspend operator fun invoke(ticketId: Int, userName: String) =
        ticketRepository.getTicketDetails(ticketId, userName)
}