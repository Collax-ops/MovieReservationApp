package com.example.moviereservationsystem.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys


@Serializable
@JsonIgnoreUnknownKeys
data class MovieListResponse(
    val page: Int,
    val results: List<MovieDto>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
)
