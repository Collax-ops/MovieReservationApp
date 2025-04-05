package com.example.moviereservationsystem.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class GenreListResponse (
    val genres: List<GenreDto>
)