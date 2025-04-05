package com.example.moviereservationsystem.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movieId")
    val id: Int,
    @ColumnInfo(name = "Title")
    val title: String,
)