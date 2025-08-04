package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.repository.MovieRepository
import javax.inject.Inject

class GetGenresOfMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke () = movieRepository.getGenresOfMovieList()
}