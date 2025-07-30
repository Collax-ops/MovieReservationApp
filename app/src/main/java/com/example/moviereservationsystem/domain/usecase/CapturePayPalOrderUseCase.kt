package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.repository.PayPalRepository
import javax.inject.Inject

class CapturePayPalOrderUseCase @Inject constructor(
    private val paypalRepository: PayPalRepository
) {
    suspend operator fun invoke(orderId: String) = paypalRepository.captureOrder(orderId)
}