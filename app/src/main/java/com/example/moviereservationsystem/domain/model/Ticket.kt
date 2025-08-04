package com.example.moviereservationsystem.domain.model

import kotlinx.datetime.LocalDateTime

data class Ticket(
    val ticketId: Int,
    val userId: String?,
    val scheduleId: Int,
    val totalPrice: Double,
    val purchaseDate: LocalDateTime
)
