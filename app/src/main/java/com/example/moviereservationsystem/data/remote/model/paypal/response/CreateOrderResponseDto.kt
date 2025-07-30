package com.example.moviereservationsystem.data.remote.model.paypal.response


import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@OptIn(ExperimentalSerializationApi::class)
@JsonIgnoreUnknownKeys
@Serializable
data class CreateOrderResponseDto(
    val id: String,
    val intent: String? = null, // <- opcional
    val status: String,
    @SerialName("payment_source")
    val paymentSource: PaymentSourceResponseDto? = null,
    @SerialName("purchase_units")
    val purchaseUnits: List<PurchaseUnitResponseDto>? = null, // <- opcional
    val links: List<LinkDto>
)
