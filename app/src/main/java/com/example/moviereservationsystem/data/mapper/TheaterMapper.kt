package com.example.moviereservationsystem.data.mapper

import com.example.moviereservationsystem.data.local.model.entities.TheaterEntity
import com.example.moviereservationsystem.domain.model.Theaters

fun TheaterEntity.toDomain(): Theaters {
    return Theaters(
        id = this.id,
        theaterName = this.theaterName,
        theaterLocation = this.theaterLocation,
        totalSeats = this.totalSeats
    )
}