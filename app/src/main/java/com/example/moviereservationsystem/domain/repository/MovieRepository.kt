package com.example.moviereservationsystem.domain.repository

import com.example.moviereservationsystem.domain.model.Movie
import kotlinx.coroutines.flow.Flow


interface MovieRepository  {
   fun getMoviesNowPlaying(): Flow<Result<List<Movie>>>
}