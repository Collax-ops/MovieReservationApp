package com.example.moviereservationsystem.ui.screens.MovieSchedule

import com.example.moviereservationsystem.ui.screens.MovieSchedule.model.DatesUiModel

data class MovieScheduleUiState (
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val dates: List<DatesUiModel> = emptyList(),
)