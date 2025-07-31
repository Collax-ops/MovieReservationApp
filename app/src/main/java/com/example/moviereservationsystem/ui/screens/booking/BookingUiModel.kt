package com.example.moviereservationsystem.ui.screens.booking

import com.example.moviereservationsystem.R

data class BookingUiModel(
    val movieTitle: String,
    val date: String,
    val ticketsCount: Int,
    val totalPrice: Double,
    val posterRes: Int = R.drawable.movie_icon
)
