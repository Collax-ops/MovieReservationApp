package com.example.moviereservationsystem.data.mapper

import com.example.moviereservationsystem.data.remote.model.paypal.response.captureorder.CaptureOrderResponseDto
import com.example.moviereservationsystem.data.remote.model.paypal.response.createorder.CreateOrderResponseDto
import com.example.moviereservationsystem.domain.model.CapturedPayPalPayment
import com.example.moviereservationsystem.domain.model.PayPalOrder
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun CreateOrderResponseDto.toDomain(): PayPalOrder {
    val approvalLink = links.firstOrNull { it.rel == "payer-action" }?.href.orEmpty()
    return PayPalOrder(
        id = id.toString(),
        status = status,
        approvalLink = approvalLink
    )
}
fun CaptureOrderResponseDto.toDomainModel(): CapturedPayPalPayment {
    val capture = this.purchaseUnits
        ?.firstOrNull()
        ?.payments
        ?.captures
        ?.firstOrNull()

    return CapturedPayPalPayment(
        orderId = this.id ?: "",
        captureId = capture?.id ?: "",
        amount = capture?.amount?.value?.toDoubleOrNull() ?: 0.0,
        currency = capture?.amount?.currencyCode ?: "USD",
        status = capture?.status ?: "",
        transactionDate = capture?.createTime
            ?.let { Instant.parse(it).toLocalDateTime(TimeZone.currentSystemDefault()) }
            ?: Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    )
}