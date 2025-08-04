package com.example.moviereservationsystem.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class User (
    val id: String,
    val name: String,
    val email: String,
)