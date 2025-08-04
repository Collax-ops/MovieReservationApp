package com.example.moviereservationsystem.data.remote.model.tmdb

import kotlinx.serialization.Serializable

@Serializable
data class GenreListResponse (
    val genres: List<GenreDto>
)