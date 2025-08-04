package com.example.moviereservationsystem.domain.model


data class Theaters (
    val id: Int,
    val theaterName: String,
    val theaterLocation: String,
    val totalSeats: Int
)