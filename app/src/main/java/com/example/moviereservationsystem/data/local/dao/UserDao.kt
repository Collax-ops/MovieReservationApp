package com.example.moviereservationsystem.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.moviereservationsystem.data.local.model.UserEntity
import com.example.moviereservationsystem.domain.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertUser(user: UserEntity)
}