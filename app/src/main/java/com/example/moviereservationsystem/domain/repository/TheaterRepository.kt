package com.example.moviereservationsystem.domain.repository

import com.example.moviereservationsystem.domain.model.Theaters
import kotlinx.coroutines.flow.Flow

interface TheaterRepository {

    fun getAllTheaters() : Flow<List<Theaters>>

}