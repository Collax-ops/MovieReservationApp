package com.example.moviereservationsystem.data.local.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "tickets_seats",
    primaryKeys = ["ticketId", "seatId"],
    foreignKeys = [
        ForeignKey(
            entity = TicketEntity::class,
            parentColumns = ["ticketId"],
            childColumns = ["ticketId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SeatEntity::class,
            parentColumns = ["SeatID"],
            childColumns = ["seatId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("seatId")
    ]
)
data class TicketSeatCrossRef(
    val ticketId: Int,
    val seatId: String
)
