package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.model.MovieSchedule
import com.example.moviereservationsystem.domain.repository.MovieScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieSchedulesUseCase @Inject constructor(
    private val movieScheduleRepository: MovieScheduleRepository
) {
    operator fun invoke(movieId: Int, theaterId: Int) : Flow<List<MovieSchedule>> =
        movieScheduleRepository.getSchedulesForMovieAndTheater(movieId, theaterId)
}