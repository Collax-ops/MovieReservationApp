package com.example.moviereservationsystem.data.remote.model.paypal.response

import kotlinx.serialization.Serializable

@Serializable
data class CaptureOrderResponseDto (
    val id: String? = null,
    val status: String? = null,
    val intent: String
)
