package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.model.Payment
import com.example.moviereservationsystem.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserPaymentsUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {
    operator fun invoke(userId: Int): Flow<List<Payment>> = paymentRepository.getPaymentHistory(userId)
}