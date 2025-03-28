package com.example.moviereservationsystem.ui.screens.movieSchedule

import com.example.moviereservationsystem.ui.screens.movieSchedule.model.DatesUiModel

data class MovieScheduleUiState (
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val dates: List<DatesUiModel> = emptyList(),
)