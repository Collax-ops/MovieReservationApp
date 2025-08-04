package com.example.moviereservationsystem.data.local.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity(
    tableName = "tickets",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["UserID"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MovieScheduleEntity::class,
            parentColumns = ["scheduleId"],
            childColumns = ["scheduleId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("userId"),
        Index("scheduleId")
    ]
)
data class TicketEntity(
    @PrimaryKey(autoGenerate = true)
    val ticketId: Int = 0,
    val userId: String?,
    val scheduleId: Int,
    val totalPrice: Double,
    val purchaseDate: LocalDateTime
)