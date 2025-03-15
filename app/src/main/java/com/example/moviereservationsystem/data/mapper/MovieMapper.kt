package com.example.moviereservationsystem.data.mapper

import com.example.moviereservationsystem.data.model.MovieDto
import com.example.moviereservationsystem.domain.model.Movie
import dagger.Provides
import javax.inject.Inject


class MovieMapper @Inject constructor()  {
    fun mapToDomain(dto: MovieDto) : Movie {
        return Movie(
            id = dto.id,
            genreIds = dto.genreIds,
            title = dto.title,
            posterPath = dto.posterPath
        )
    }
}