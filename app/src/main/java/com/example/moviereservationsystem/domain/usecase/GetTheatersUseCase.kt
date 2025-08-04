package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.repository.TheaterRepository
import javax.inject.Inject

class GetTheatersUseCase @Inject constructor(
    private val theaterRepository: TheaterRepository
) {
    operator fun invoke() = theaterRepository.getAllTheaters()
}