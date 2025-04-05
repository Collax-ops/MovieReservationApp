package com.example.moviereservationsystem.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys


@Serializable
@JsonIgnoreUnknownKeys
data class MovieDto(
    val id: Int,
    @SerialName("genre_ids")
    val genreIds: List<Int>, val title: String,
    @SerialName("poster_path") val posterPath: String?
)
