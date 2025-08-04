package com.example.moviereservationsystem.data.remote.model.paypal.request

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class AmountDto (
    @SerialName("currency_code")
    val currencyCode: String,
    @SerialName("value")
    val value: String
)