package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.model.Payment
import com.example.moviereservationsystem.domain.repository.PaymentRepository
import javax.inject.Inject

class SavePaymentUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {
    suspend operator fun invoke(payment: Payment) = paymentRepository.insert(payment)
}