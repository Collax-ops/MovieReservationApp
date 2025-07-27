package com.example.moviereservationsystem.domain.repository

import com.example.moviereservationsystem.domain.model.MovieSchedule
import kotlinx.coroutines.flow.Flow

interface MovieScheduleRepository {

    fun getSchedulesForMovieAndTheater(movieId: Int, theaterId: Int): Flow<List<MovieSchedule>>

}