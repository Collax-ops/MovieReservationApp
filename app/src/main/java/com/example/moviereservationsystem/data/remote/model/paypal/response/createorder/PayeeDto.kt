package com.example.moviereservationsystem.data.remote.model.paypal.response.createorder

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PayeeDto(
    @SerialName("email_address")
    val emailAddress: String,
    @SerialName("merchant_id")
    val merchantId: String
)