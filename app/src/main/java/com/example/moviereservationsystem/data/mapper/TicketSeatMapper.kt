package com.example.moviereservationsystem.data.mapper

import com.example.moviereservationsystem.data.local.model.TicketSeatCrossRef
import com.example.moviereservationsystem.domain.model.TicketSeat

fun TicketSeatCrossRef.toDomain(): TicketSeat = TicketSeat(ticketId, seatId)

fun TicketSeat.toEntity(): TicketSeatCrossRef = TicketSeatCrossRef(ticketId, seatId)
