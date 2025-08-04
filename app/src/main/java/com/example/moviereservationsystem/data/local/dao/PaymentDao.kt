package com.example.moviereservationsystem.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moviereservationsystem.data.local.model.entities.PaymentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {
    @Insert
    suspend fun insert(payment: PaymentEntity)

    @Query("SELECT * FROM payments " +
            "WHERE ticketId " +
            "IN (SELECT ticketId FROM tickets WHERE userId = :userId) " +
            "ORDER BY transactionDate DESC")
    fun getPaymentHistory(userId: String): Flow<List<PaymentEntity>>
}