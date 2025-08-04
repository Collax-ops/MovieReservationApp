package com.example.moviereservationsystem.domain.repository

import com.example.moviereservationsystem.domain.model.Genre
import com.example.moviereservationsystem.domain.model.Movie
import kotlinx.coroutines.flow.Flow


interface MovieRepository  {
   fun getMoviesNowPlaying(): Flow<Result<List<Movie>>>

   fun getGenresOfMovieList(): Flow<Result<List<Genre>>>
}