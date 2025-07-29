package com.example.moviereservationsystem.data.remote.model.paypal.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaymentSourceDto (
    @SerialName("paypal")
    val paypal: PayPalDto
)