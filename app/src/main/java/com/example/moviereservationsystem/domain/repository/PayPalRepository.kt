package com.example.moviereservationsystem.domain.repository

interface PayPalRepository {
    suspend fun getAccessToken(): String
    suspend fun createOrder(amount: Double): String
    suspend fun captureOrder(orderId: String): String
}