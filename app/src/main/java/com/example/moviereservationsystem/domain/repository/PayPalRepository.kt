package com.example.moviereservationsystem.domain.repository

import com.example.moviereservationsystem.domain.model.CapturedPayPalPayment

interface PayPalRepository {
    suspend fun getAccessToken(): String
    suspend fun createOrder(amount: Double): String
    suspend fun captureOrder(orderId: String): CapturedPayPalPayment
}