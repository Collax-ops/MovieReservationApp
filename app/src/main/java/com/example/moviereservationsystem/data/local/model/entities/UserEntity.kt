package com.example.moviereservationsystem.data.local.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "UserID")
    val id: String,
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Email")
    val email: String,
)