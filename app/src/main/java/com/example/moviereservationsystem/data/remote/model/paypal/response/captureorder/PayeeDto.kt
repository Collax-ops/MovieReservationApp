package com.example.moviereservationsystem.data.remote.model.paypal.response.captureorder

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@JsonIgnoreUnknownKeys
@Serializable
data class PayeeDto(
    @SerialName("email_address")
    val emailAddress: String,
    @SerialName("merchant_id")
    val merchantId: String
)
