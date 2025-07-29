package com.example.moviereservationsystem.data.remote.model.paypal.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderRequestDto (
    val intent: String,
    @SerialName("payment_source")
    val paymentSource: PaymentSourceDto,
    @SerialName("purchase_units")
    val purchaseUnits: List<PurchaseUnitRequestDto>
)
