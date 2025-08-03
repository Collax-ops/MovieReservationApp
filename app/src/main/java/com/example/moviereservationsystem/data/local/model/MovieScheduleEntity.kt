package com.example.moviereservationsystem.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity(
    tableName = "movie_schedules",
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
    indices = [
        Index("theaterId"),
        Index("movieId")
    ]
)
data class MovieScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val scheduleId: Int = 0,

    val theaterId: Int,
    val movieId: Int,

    val startTime: String,
    val endTime: String
)