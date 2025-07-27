package com.example.moviereservationsystem.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Seats",
    foreignKeys = [ForeignKey(
        entity = TheaterEntity::class,
        parentColumns = ["TheaterId"],
        childColumns = ["TheaterID"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class SeatEntity(
    @PrimaryKey
    @ColumnInfo(name = "SeatID")
    val seatId: String,

    @ColumnInfo(name = "TheaterID")
    val theaterId: Int,

    @ColumnInfo(name = "Row")
    val row: Char,

    @ColumnInfo(name = "SeatNumber")
    val seatNumber: Int,

    @ColumnInfo(name = "IsAvailable")
    val isAvailable: Boolean
) {
    companion object {
        fun from(row: Char, seatNumber: Int, theaterId: Int, isAvailable: Boolean = true): SeatEntity {
            return SeatEntity(
                seatId = "$row$seatNumber-$theaterId",
                row = row,
                seatNumber = seatNumber,
                theaterId = theaterId,
                isAvailable = isAvailable
            )
        }
    }
}