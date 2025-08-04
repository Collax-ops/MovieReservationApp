package com.example.moviereservationsystem.data.local.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "MovieId")
    val id: Int,
    @ColumnInfo(name = "Title")
    val title: String,
)