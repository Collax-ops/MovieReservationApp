package com.example.moviereservationsystem.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "movie_schedules",
    primaryKeys = ["theaterId", "movieId"],
    foreignKeys = [
        ForeignKey(
            entity = TheaterEntity::class,
            parentColumns = ["TheaterId"],
            childColumns = ["theaterId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["MovieId"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["theaterId"]), Index(value = ["movieId"])]
)
data class MovieScheduleEntity(
    @ColumnInfo(name = "theaterId") val theaterId: Int,
    @ColumnInfo(name = "movieId") val movieId: Int,
    @ColumnInfo(name = "startTime") val startTime: String,
    @ColumnInfo(name = "endTime") val endTime: String
)
