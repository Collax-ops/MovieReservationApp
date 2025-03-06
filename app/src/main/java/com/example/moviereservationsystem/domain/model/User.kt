package com.example.moviereservationsystem.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "UserID")
    val id: Int = 0,
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Email")
    val email: String,
    @ColumnInfo(name = "Password")
    val password: String,
    @ColumnInfo(name = "Phone_Number")
    val phoneNumber: Long
)