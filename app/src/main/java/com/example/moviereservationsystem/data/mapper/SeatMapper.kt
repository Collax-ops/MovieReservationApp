package com.example.moviereservationsystem.data.mapper

import com.example.moviereservationsystem.data.local.model.SeatEntity
import com.example.moviereservationsystem.domain.model.Seats

fun SeatEntity.toDomain(): Seats {
    return Seats(
        seatId = this.seatId,
        row = this.row,
        number = this.seatNumber,
        isAvailable = this.isAvailable
    )
}