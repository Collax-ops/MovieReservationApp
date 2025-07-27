package com.example.moviereservationsystem.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


data class Seats(
    val seatId: String,
    val row: Char,
    val number: Int,
    val isAvailable: Boolean
)