package com.example.moviereservationsystem.data.mapper

import com.example.moviereservationsystem.data.local.model.MovieScheduleEntity
import com.example.moviereservationsystem.domain.model.MovieSchedule

fun MovieScheduleEntity.toDomain(): MovieSchedule {
    return MovieSchedule(
        theaterId = this.theaterId,
        movieId = this.movieId,
        startTime = this.startTime,
        endTime = this.endTime,
    )
}