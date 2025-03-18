package com.example.moviereservationsystem.ui.home.model

data class MovieUiModel(
    val title: String,
    val posterPath: String?,
    val genreIds: List<Int>
)
