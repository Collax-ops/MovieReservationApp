package com.example.moviereservationsystem.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviereservationsystem.data.local.dao.MovieDao
import com.example.moviereservationsystem.data.local.dao.MovieScheduleDao
import com.example.moviereservationsystem.data.local.dao.PaymentDao
import com.example.moviereservationsystem.data.local.dao.SeatDao
import com.example.moviereservationsystem.data.local.dao.TheaterDao
import com.example.moviereservationsystem.data.local.dao.TicketDao
import com.example.moviereservationsystem.data.local.dao.TicketSeatDao
import com.example.moviereservationsystem.data.local.dao.UserDao
import com.example.moviereservationsystem.data.local.model.entities.MovieEntity
import com.example.moviereservationsystem.data.local.model.entities.MovieScheduleEntity
import com.example.moviereservationsystem.data.local.model.entities.PaymentEntity
import com.example.moviereservationsystem.data.local.model.entities.SeatEntity
import com.example.moviereservationsystem.data.local.model.entities.TheaterEntity
import com.example.moviereservationsystem.data.local.model.entities.TicketEntity
import com.example.moviereservationsystem.data.local.model.entities.TicketSeatCrossRef
import com.example.moviereservationsystem.data.local.model.entities.UserEntity

@Database(
    entities = [
        UserEntity::class,
        MovieEntity::class,
        TheaterEntity::class,
        MovieScheduleEntity::class,
        SeatEntity::class,
        PaymentEntity::class,
        TicketEntity::class,
        TicketSeatCrossRef::class
        ],
    version = 4,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun movieDao() : MovieDao
    abstract fun theaterDao() : TheaterDao
    abstract fun movieScheduleDao() : MovieScheduleDao
    abstract fun seatDao() : SeatDao
    abstract fun paymentDao() : PaymentDao
    abstract fun ticketDao() : TicketDao
    abstract fun ticketSeatDao(): TicketSeatDao
}