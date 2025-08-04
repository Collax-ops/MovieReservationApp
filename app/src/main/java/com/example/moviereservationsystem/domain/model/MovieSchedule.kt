package com.example.moviereservationsystem.domain.model

data class MovieSchedule (
    val scheduleId: Int,
    val movieId: Int,
    val theaterId: Int,
    val startTime: String,
    val endTime: String,
)