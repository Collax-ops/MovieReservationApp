package com.example.moviereservationsystem.data.remote.model.paypal.response.createorder


import com.example.moviereservationsystem.data.remote.model.paypal.request.AmountDto
import com.example.moviereservationsystem.data.remote.model.paypal.response.createorder.PayeeDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PurchaseUnitResponseDto(
    @SerialName("reference_id")
    val referenceId: String,
    val amount: AmountDto,
    val payee: PayeeDto
)