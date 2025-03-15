package com.example.moviereservationsystem.ui.home

data class HomeUiState (
    val isLoading: Boolean = false,
    val movieList: List<MovieUiModel> = emptyList(),
)