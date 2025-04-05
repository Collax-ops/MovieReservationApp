package com.example.moviereservationsystem.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    val id: Int,
    val name: String
)
