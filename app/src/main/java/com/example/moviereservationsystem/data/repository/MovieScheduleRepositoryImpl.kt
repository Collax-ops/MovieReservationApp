package com.example.moviereservationsystem.data.repository

import com.example.moviereservationsystem.data.local.dao.MovieScheduleDao
import com.example.moviereservationsystem.data.mapper.toDomain
import com.example.moviereservationsystem.domain.repository.MovieScheduleRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieScheduleRepositoryImpl @Inject constructor(
    private val movieScheduleDao: MovieScheduleDao
) : MovieScheduleRepository {
    override fun getSchedulesForMovieAndTheater(movieId: Int, theaterId: Int) =
        movieScheduleDao.getSchedulesForMovieAndTheater(movieId, theaterId).map { scheduleEntities ->
            scheduleEntities.map {
                it.toDomain()
            }
        }
}