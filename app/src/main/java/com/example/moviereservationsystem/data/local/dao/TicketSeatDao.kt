package com.example.moviereservationsystem.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.moviereservationsystem.data.local.model.entities.TicketSeatCrossRef

@Dao
interface TicketSeatDao {
    @Insert
    suspend fun insertTicketSeats(refs: List<TicketSeatCrossRef>)
}