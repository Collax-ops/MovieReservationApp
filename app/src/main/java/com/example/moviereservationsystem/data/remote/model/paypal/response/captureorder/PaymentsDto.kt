package com.example.moviereservationsystem.data.remote.model.paypal.response.captureorder

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@JsonIgnoreUnknownKeys
@Serializable
data class PaymentsDto(
    val captures: List<CaptureDto>? = null
)
