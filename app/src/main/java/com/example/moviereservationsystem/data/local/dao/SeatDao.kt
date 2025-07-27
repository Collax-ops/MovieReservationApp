package com.example.moviereservationsystem.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviereservationsystem.data.local.model.SeatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SeatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeats(seats: List<SeatEntity>)

    @Query("SELECT * FROM Seats WHERE SeatID = :seatId")
    suspend fun getSeatById(seatId: String): SeatEntity

    @Query("UPDATE Seats SET IsAvailable = :available WHERE SeatID = :seatId")
    suspend fun updateAvailability(seatId: String, available: Boolean)

    @Query("SELECT * FROM Seats WHERE TheaterID = :theaterId ORDER BY `Row`, SeatNumber")
    fun observeSeats(theaterId: Int): Flow<List<SeatEntity>>
}