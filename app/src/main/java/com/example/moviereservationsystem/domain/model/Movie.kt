package com.example.moviereservationsystem.domain.model

data class Movie (
    val id: Int,
    val genreIds: List<Int>,
    val title: String,
    val posterPath: String?
)