package com.example.moviereservationsystem.data.local

import androidx.room.Dao
import androidx.room.Insert
import com.example.moviereservationsystem.domain.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)
}