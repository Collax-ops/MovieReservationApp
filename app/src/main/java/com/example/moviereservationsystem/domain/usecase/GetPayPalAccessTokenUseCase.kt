package com.example.moviereservationsystem.domain.usecase

import com.example.moviereservationsystem.domain.repository.PayPalRepository
import javax.inject.Inject

class GetPayPalAccessTokenUseCase @Inject constructor(
    private val repository: PayPalRepository
) {
    suspend operator fun invoke() = repository.getAccessToken()
}