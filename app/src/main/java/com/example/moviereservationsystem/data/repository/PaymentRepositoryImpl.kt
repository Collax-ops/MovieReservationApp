package com.example.moviereservationsystem.data.repository

import com.example.moviereservationsystem.data.local.dao.PaymentDao
import com.example.moviereservationsystem.data.mapper.toDomain
import com.example.moviereservationsystem.data.mapper.toEntity
import com.example.moviereservationsystem.domain.model.Payment
import com.example.moviereservationsystem.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val paymentDao: PaymentDao
) : PaymentRepository {
    override suspend fun insert(payment: Payment) {
        paymentDao.insert(payment.toEntity())
    }

    override fun getPaymentHistory(userId: String): Flow<List<Payment>> {
        return paymentDao.getPaymentHistory(userId).map { list -> list.map { it.toDomain() } }
    }

}