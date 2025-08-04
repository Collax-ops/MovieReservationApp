package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.repository.SeatRepository
import javax.inject.Inject

class GetSeatsUseCase  @Inject constructor(
    private val repo: SeatRepository
) {
    operator fun invoke(theaterId: Int) = repo.observeSeats(theaterId)
}