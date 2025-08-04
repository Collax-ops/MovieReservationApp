package com.example.moviereservationsystem.data.remote.model.paypal.request

import kotlinx.serialization.Serializable

@Serializable
data class PurchaseUnitRequestDto (
    val amount: AmountDto
)