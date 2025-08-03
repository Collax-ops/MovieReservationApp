package com.example.moviereservationsystem.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.moviereservationsystem.data.local.model.TicketEntity
import com.example.moviereservationsystem.data.local.model.TicketSeatCrossRef
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime

@Dao
interface TicketDao {
    @Insert
    suspend fun insertTicket(ticket: TicketEntity) : Long

    @Query("""
        SELECT ts.seatId
        FROM tickets_seats ts
        JOIN tickets t ON ts.ticketId = t.ticketId
        WHERE t.scheduleId = :scheduleId
    """)
    fun getOccupiedSeats(scheduleId: Int): Flow<List<String>>

    @Transaction
    @Query("SELECT * FROM TICKETS " +
            "WHERE userId = :userId " +
            "ORDER BY purchaseDate DESC")
    fun getUserTickets(userId: Int): Flow<List<TicketEntity>>
}