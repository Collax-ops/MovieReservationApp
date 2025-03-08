package com.example.moviereservationsystem.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User (
    @PrimaryKey
    @ColumnInfo(name = "UserID")
    val id: String,
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Email")
    val email: String,
    @ColumnInfo(name = "Password")
    val password: String
)