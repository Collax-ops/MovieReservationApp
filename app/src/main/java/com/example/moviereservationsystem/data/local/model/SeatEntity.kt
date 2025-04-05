package com.example.moviereservationsystem.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.moviereservationsystem.domain.model.Theaters

@Entity(
    tableName = "Seats",
    foreignKeys = [ForeignKey(
        entity = Theaters::class,
        parentColumns = ["TheaterId"],
        childColumns = ["TheaterId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class SeatEntity(
    @PrimaryKey
    @ColumnInfo(name = "SeatID")
    val seatId: String,

    @ColumnInfo(name = "TheaterID")
    val theaterId: Int,

    @ColumnInfo(name = "IsAvailable")
    val isAvailable: Boolean
) {
    companion object {
        fun generateSeatId(row: Char, seatNumber: Int): String {
            return "$row$seatNumber"
        }
    }
}