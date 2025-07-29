package com.example.moviereservationsystem.data.remote.model.paypal.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class PaymentSourceResponseDto(
    val paypal: JsonObject? = null
)