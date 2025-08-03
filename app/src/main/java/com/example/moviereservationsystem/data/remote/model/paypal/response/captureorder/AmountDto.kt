package com.example.moviereservationsystem.data.remote.model.paypal.response.captureorder

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@JsonIgnoreUnknownKeys
@Serializable
data class AmountDto (
    @SerialName("currency_code")
    val currencyCode: String,
    val value: String
)