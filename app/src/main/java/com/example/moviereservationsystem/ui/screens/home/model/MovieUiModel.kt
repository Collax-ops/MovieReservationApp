package com.example.moviereservationsystem.ui.screens.home.model

data class MovieUiModel(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val genreIds: List<Int>
)
