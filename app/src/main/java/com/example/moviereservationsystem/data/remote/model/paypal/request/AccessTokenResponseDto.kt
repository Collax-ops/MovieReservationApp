package com.example.moviereservationsystem.data.remote.model.paypal.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable;

@Serializable
data class AccessTokenResponseDto (
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("expires_in")
    val expiresIn: Long
)
