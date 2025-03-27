package com.example.moviereservationsystem.ui.screens.MovieSchedule.model

data class DatesUiModel(
    val month: String,
    val date: String,
    val dayofWeek: String,
    val isToday: Boolean,
    val isSelected: Boolean = false
)