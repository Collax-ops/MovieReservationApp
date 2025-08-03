package com.example.moviereservationsystem.data.mapper

import com.example.moviereservationsystem.data.local.model.TicketEntity
import com.example.moviereservationsystem.domain.model.Ticket

fun TicketEntity.toDomain(): Ticket = Ticket(
    ticketId = ticketId,
    userId = userId.toString(),
    scheduleId = scheduleId,
    totalPrice = totalPrice,
    purchaseDate = purchaseDate
)

fun Ticket.toEntity(): TicketEntity = TicketEntity(
    ticketId = ticketId,
    userId = userId,
    scheduleId = scheduleId,
    totalPrice = totalPrice,
    purchaseDate = purchaseDate
)