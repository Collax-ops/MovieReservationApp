package com.example.moviereservationsystem.ui.screens.movieSchedule


import com.example.moviereservationsystem.ui.screens.movieSchedule.model.DatesUiModel
import com.example.moviereservationsystem.ui.screens.movieSchedule.model.MovieScheduleUiModel
import com.example.moviereservationsystem.ui.screens.movieSchedule.model.TheatersUiModel

data class MovieScheduleUiState (
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val selectedTheaterId: Int? = null,
    val dates: List<DatesUiModel> = emptyList(),
    val theaters: List<TheatersUiModel> = emptyList(),
    val movieSchedules: List<MovieScheduleUiModel> = emptyList(),
    val expandedState: Map<Int, Boolean> = emptyMap()
)