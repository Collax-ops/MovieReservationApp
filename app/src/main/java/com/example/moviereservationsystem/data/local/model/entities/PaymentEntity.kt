package com.example.moviereservationsystem.data.local.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity(
    tableName = "payments",
    foreignKeys = [
        ForeignKey(
            entity = TicketEntity::class,
            parentColumns = ["ticketId"],
            childColumns = ["ticketId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [ Index("ticketId") ]
)
data class PaymentEntity(
    @PrimaryKey(autoGenerate = true)
    val paymentId: Int = 0,

    val ticketId: Int,
    val amount: Double,
    val paymentMethod: String,
    val paymentStatus: String,
    val transactionDate: LocalDateTime
)