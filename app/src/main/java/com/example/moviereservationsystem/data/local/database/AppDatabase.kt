package com.example.moviereservationsystem.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviereservationsystem.data.local.dao.MovieDao
import com.example.moviereservationsystem.data.local.dao.MovieScheduleDao
import com.example.moviereservationsystem.data.local.dao.SeatDao
import com.example.moviereservationsystem.data.local.dao.TheaterDao
import com.example.moviereservationsystem.data.local.dao.UserDao
import com.example.moviereservationsystem.data.local.model.MovieEntity
import com.example.moviereservationsystem.data.local.model.MovieScheduleEntity
import com.example.moviereservationsystem.data.local.model.SeatEntity
import com.example.moviereservationsystem.data.local.model.TheaterEntity
import com.example.moviereservationsystem.data.local.model.UserEntity

@Database(
    entities = [
        UserEntity::class,
        MovieEntity::class,
        TheaterEntity::class,
        MovieScheduleEntity::class,
        SeatEntity::class
        ],
    version = 3,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun movieDao() : MovieDao
    abstract fun theaterDao() : TheaterDao
    abstract fun movieScheduleDao() : MovieScheduleDao
    abstract fun seatDao() : SeatDao
}