package com.example.moviereservationsystem.data.mapper

import com.example.moviereservationsystem.data.local.model.entities.MovieEntity
import com.example.moviereservationsystem.data.remote.model.tmdb.MovieDto
import com.example.moviereservationsystem.domain.model.Movie
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

   fun mapMoviesToEntities(movies: List<Movie>): List<MovieEntity> {
        return movies.map { movie ->
            MovieEntity(
                id = movie.id,
                title = movie.title
            )
        }
   }
}