package com.example.moviereservationsystem.data.repository

import com.example.moviereservationsystem.data.local.dao.TheaterDao
import com.example.moviereservationsystem.data.local.model.MovieEntity
import com.example.moviereservationsystem.data.mapper.toDomain
import com.example.moviereservationsystem.domain.model.Theaters
import com.example.moviereservationsystem.domain.repository.TheaterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TheaterRepositoryImpl @Inject constructor(
    private val theaterDao: TheaterDao
) : TheaterRepository {
    override fun getAllTheaters(): Flow<List<Theaters>> {
        return theaterDao.getAllTheaters().map { theaters ->
            theaters.map { it.toDomain() }
        }
    }
}