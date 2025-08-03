package com.example.moviereservationsystem.data.remote.model.paypal.response.captureorder

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@JsonIgnoreUnknownKeys
@Serializable
data class CaptureDto(
    val id: String,
    val status: String,
    val amount: AmountDto,
    @SerialName("create_time")
    val createTime: String,
    @SerialName("update_time")
    val updateTime: String
)
