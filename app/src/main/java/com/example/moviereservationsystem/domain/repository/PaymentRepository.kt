package com.example.moviereservationsystem.domain.repository


import com.example.moviereservationsystem.domain.model.Payment
import kotlinx.coroutines.flow.Flow


interface PaymentRepository {
    suspend fun insert(payment: Payment)

    fun getPaymentHistory(userId: Int): Flow<List<Payment>>
}