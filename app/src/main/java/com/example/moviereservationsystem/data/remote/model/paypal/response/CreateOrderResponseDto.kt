package com.example.moviereservationsystem.data.remote.model.paypal.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderResponseDto (
    val id: Long,
    val intent: String,
    val status: String,
    @SerialName("payment_source")
    val paymentSource: PaymentSourceResponseDto?,
    @SerialName("purchase_units")
    val purchaseUnits: List<PurchaseUnitResponseDto>,
    val links: List<LinkDto>
)
