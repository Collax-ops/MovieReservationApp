package com.example.moviereservationsystem.ui.screens.movieSchedule.model

data class DatesUiModel(
    val month: String,
    val date: String,
    val dayOfWeek: String,
    val isToday: Boolean,
    val isSelected: Boolean = false
)