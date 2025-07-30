package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.repository.PayPalRepository
import javax.inject.Inject

class CreatePayPalOrderUseCase @Inject constructor(
    private val payPalRepository: PayPalRepository
) {
    suspend operator fun invoke (amount: Double) = payPalRepository.createOrder(amount)
}