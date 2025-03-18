package com.example.moviereservationsystem.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GenreListResponse (
    val genres: List<GenreDto>
)