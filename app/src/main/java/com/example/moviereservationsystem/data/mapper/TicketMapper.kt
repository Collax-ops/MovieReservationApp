package com.example.moviereservationsystem.data.mapper

import com.example.moviereservationsystem.data.local.model.dtos.BookingHistoryProjection
import com.example.moviereservationsystem.data.local.model.dtos.TicketDetailsProjection
import com.example.moviereservationsystem.data.local.model.entities.TicketEntity
import com.example.moviereservationsystem.domain.model.BookingHistory
import com.example.moviereservationsystem.domain.model.Ticket
import com.example.moviereservationsystem.domain.model.TicketDetail

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

fun TicketDetailsProjection.toDomain(userName: String): TicketDetail {
    return TicketDetail(
        movieTitle = movieTitle,
        userName = userName,
        date = purchaseDate.substringBefore("T"),
        showTime = "$showStartTime - $showEndTime",
        seats = seatList.split(","),
        totalPrice = totalPrice
    )
}

fun BookingHistoryProjection.toDomain(): BookingHistory {
    return BookingHistory(
        ticketId = ticketId,
        scheduleId = scheduleId,
        totalPrice = totalPrice,
        purchaseDate = purchaseDate,
        movieId = movieId,
        movieTitle = movieTitle,
        seatCount = seatCount
    )
}