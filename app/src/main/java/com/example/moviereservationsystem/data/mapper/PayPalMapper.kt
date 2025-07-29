package com.example.moviereservationsystem.data.mapper

import com.example.moviereservationsystem.data.remote.model.paypal.response.CreateOrderResponseDto
import com.example.moviereservationsystem.domain.model.PayPalOrder

fun CreateOrderResponseDto.toDomain(): PayPalOrder {
    val approvalLink = links.firstOrNull { it.rel == "payer-action" }?.href.orEmpty()
    return PayPalOrder(
        id = id.toString(),
        status = status,
        approvalLink = approvalLink
    )
}