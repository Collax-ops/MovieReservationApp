package com.example.moviereservationsystem.data.remote.model.tmdb

import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    val id: Int,
    val name: String
)
