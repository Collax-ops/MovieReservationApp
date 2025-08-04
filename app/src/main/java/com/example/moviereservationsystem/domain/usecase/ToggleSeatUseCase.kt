package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.repository.SeatRepository
import javax.inject.Inject

class ToggleSeatUseCase @Inject constructor(
    private val repo: SeatRepository
)  {
    suspend operator fun invoke(seatId: String) = repo.toggleAvailability(seatId)
}