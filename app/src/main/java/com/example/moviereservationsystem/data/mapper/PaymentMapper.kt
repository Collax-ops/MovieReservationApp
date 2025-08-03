package com.example.moviereservationsystem.data.mapper

import com.example.moviereservationsystem.data.local.model.PaymentEntity
import com.example.moviereservationsystem.domain.model.Payment

fun PaymentEntity.toDomain(): Payment = Payment(
    paymentId,ticketId, amount, paymentMethod, paymentStatus, transactionDate
)

fun Payment.toEntity(): PaymentEntity = PaymentEntity(
   paymentId,ticketId, amount, paymentMethod, paymentStatus, transactionDate
)