package com.example.moviereservationsystem.data.remote.model.paypal.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExperienceContextDto (
    @SerialName("return_url")
    val returnUrl: String,
    @SerialName("cancel_url")
    val cancelUrl: String,
    @SerialName("user_action")
    val userAction: String
)