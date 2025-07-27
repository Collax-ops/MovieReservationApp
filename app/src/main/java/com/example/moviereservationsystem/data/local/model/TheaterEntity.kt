package com.example.moviereservationsystem.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Theaters")
data class TheaterEntity(
    @PrimaryKey
    @ColumnInfo(name = "TheaterId")
    val id: Int,

    @ColumnInfo(name = "Name")
    val theaterName: String,

    @ColumnInfo(name = "Location")
    val theaterLocation: String,

    @ColumnInfo(name = "TotalSeats")
    val totalSeats: Int
)