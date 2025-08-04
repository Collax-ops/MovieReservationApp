package com.example.moviereservationsystem.ui.screens.home

import com.example.moviereservationsystem.ui.screens.home.model.GenreUiModel
import com.example.moviereservationsystem.ui.screens.home.model.MovieUiModel

data class HomeUiState (
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val movies: List<MovieUiModel> = emptyList(),
    val genres: List<GenreUiModel> = emptyList(),
    val selectedGenre: Int = 0,
    val filteredMovies: List<MovieUiModel> = emptyList()
)