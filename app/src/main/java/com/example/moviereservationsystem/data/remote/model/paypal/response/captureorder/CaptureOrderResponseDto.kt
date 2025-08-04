package com.example.moviereservationsystem.data.remote.model.paypal.response.captureorder

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@OptIn(ExperimentalSerializationApi::class)
@JsonIgnoreUnknownKeys
@Serializable
data class CaptureOrderResponseDto (
    val id: String? = null,
    val status: String? = null,
    val intent: String? = null,
    @SerialName("purchase_units")
    val purchaseUnits: List<PurchaseUnitDto>? = null,
    @SerialName("create_time")
    val createTime: String? = null,
    @SerialName("update_time")
    val updateTime: String? = null

)