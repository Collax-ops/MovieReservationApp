package com.example.moviereservationsystem.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviereservationsystem.data.local.dao.UserDao
import com.example.moviereservationsystem.data.local.model.UserEntity
import com.example.moviereservationsystem.domain.model.User

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao

}