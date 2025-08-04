package com.example.moviereservationsystem.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.moviereservationsystem.data.local.model.dtos.BookingHistoryProjection
import com.example.moviereservationsystem.data.local.model.dtos.TicketDetailsProjection
import com.example.moviereservationsystem.data.local.model.entities.TicketEntity
import kotlinx.coroutines.flow.Flow

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
    @Query("""
            SELECT t.ticketId, t.scheduleId, t.totalPrice, t.purchaseDate, s.movieId, 
                m.title AS movieTitle,
                (SELECT COUNT(*) FROM tickets_seats ts WHERE ts.ticketId = t.ticketId) AS seatCount
            FROM tickets t
            INNER JOIN movie_schedules s ON t.scheduleId = s.scheduleId
            INNER JOIN movies m ON s.movieId = m.movieId
            WHERE t.userId = :userId
            ORDER BY t.purchaseDate DESC
        """)
    fun getBookingHistory(userId: String): Flow<List<BookingHistoryProjection>>

    @Transaction
    @Query("""
            SELECT t.*, m.title AS movieTitle, s.startTime AS showStartTime, s.endTime AS showEndTime,
                   GROUP_CONCAT(ts.seatId) AS seatList
            FROM tickets t
            INNER JOIN movie_schedules s ON t.scheduleId = s.scheduleId
            INNER JOIN movies m ON s.movieId = m.movieId
            LEFT JOIN tickets_seats ts ON t.ticketId = ts.ticketId
            WHERE t.ticketId = :ticketId
            LIMIT 1
        """)
    suspend fun getTicketDetails(ticketId: Int): TicketDetailsProjection

}