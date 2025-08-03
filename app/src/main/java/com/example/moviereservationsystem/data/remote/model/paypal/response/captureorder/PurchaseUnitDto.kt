package com.example.moviereservationsystem.data.remote.model.paypal.response.captureorder

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@JsonIgnoreUnknownKeys
@Serializable
data class PurchaseUnitDto(
    @SerialName("amount")
    val amount: AmountDto? = null,
    @SerialName("payee")
    val payee: PayeeDto? = null,
    @SerialName("payments")
    val payments: PaymentsDto? = null
)

