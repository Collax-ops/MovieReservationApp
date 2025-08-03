package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.model.Ticket
import com.example.moviereservationsystem.domain.repository.TicketRepository
import javax.inject.Inject

class InsertTicketUseCase @Inject constructor(
    private val ticketRepository: TicketRepository
) {
    suspend operator fun invoke(ticket: Ticket): Long {
        return ticketRepository.saveTicket(ticket)
    }
}